package by.iba.impl;

import by.iba.PhotoService;
import by.iba.UserRolesService;
import by.iba.UserService;
import by.iba.config.BucketName;
import by.iba.dto.req.user.*;
import by.iba.dto.resp.PhotoForAmazonResp;
import by.iba.dto.resp.user.UserDTO;
import by.iba.dto.resp.user.UserRoleDTO;
import by.iba.entity.DocumentStatus;
import by.iba.entity.photo.Photo;
import by.iba.entity.photo.UserPhoto;
import by.iba.entity.user.UserEntity;
import by.iba.entity.user.UserRole;
import by.iba.exception.*;
import by.iba.mapper.UserMapper;
import by.iba.mapper.UserPhotoMapper;
import by.iba.repository.UserPhotoRepository;
import by.iba.repository.UserRepository;
import by.iba.s3.S3Service;
import by.iba.service.EmailService;
import by.iba.specification.user.UserSpecificationsBuilder;
import by.iba.util.SearchServiceUtil;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhotoService photoService;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
    private final UserRolesService userRolesService;
    private final SearchServiceUtil searchUtils;
    private final UserPhotoRepository userPhotoRepository;
    private final UserPhotoMapper userPhotoMapper;
    private final S3Service s3Service;


    private static final String USER_ROLE = "USER";
    private static final String AUTOPICKER_ROLE = "AUTOPICKER";
    private static final String USER_NOT_FOUND_MESSAGE = "USER_HAS_BEEN_NOT_FOUND";
    private static final String BAD_CREDENTIALS_MESSAGE = "BAD_CREDENTIALS";

    @Override
    @Transactional
    public List<UserDTO> findAll(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        searchUtils.checkDefaultValues(searchCriteriaReqDTO);
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();

        if (Objects.nonNull(searchCriteriaReqDTO.getSearch())) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)(\\w+?),");
            Matcher matcher = pattern.matcher(searchCriteriaReqDTO.getSearch() + ",");

            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }

        }

        Specification<UserEntity> specification = builder.build();

        Pageable paging = searchUtils.getPageable(searchCriteriaReqDTO);

        Page<UserEntity> pagedResult = userRepository.findAll(specification, paging);

        return pagedResult.getContent()
                .stream()
                .map(userMapper::fillFromInDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        UserEntity user = getUserById(id);

        PhotoForAmazonResp photo = photoService.findByUserId(id);
        UserDTO userDTO = userMapper.fillFromInDTO(user, photo);

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDTO save(UserReqDTO userReqDTO) {

        userRepository.findByLogin(userReqDTO.getLogin())
                .ifPresent(value -> {
                            throw new ResourceAlreadyExistException("USER_IS_ALREADY_EXIST");
                        }
                );

        UserEntity user = userMapper.fillFromRespDTO(userReqDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if(Objects.nonNull(userReqDTO.getIsAutoPicker()) && userReqDTO.getIsAutoPicker().equals("true")) {
            user.getRoles().add(userRolesService.getByName(AUTOPICKER_ROLE));
        } else {
            user.getRoles().add(userRolesService.getByName(USER_ROLE));
        }

        userRepository.save(user);
        try {
            emailService.sendEmail(user.getEmail(), "Welcome!", "Your registration is successful!");
        } catch (EmailServiceException ex) {
            throw new InvalidEmailException("This email does not exist");
        }

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public UserEntity login(UserAuthReqDTO userAuthReqDTO) {

        UserEntity user = userRepository.findByLogin(userAuthReqDTO.getLogin())
                .orElseThrow(() -> new BadCredentialsException(BAD_CREDENTIALS_MESSAGE));

        if (Objects.nonNull(user.getBannedDate())) {
            throw new UserHasBeenBannedException("User account is disabled.");
        }

        user.setDateOfLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserPersonalDataReqDTO userPersonalDataReqDTO) {

        UserEntity user = getUserById(id);

        userMapper.fillFromInDTO(user, userPersonalDataReqDTO);

        user.setDateOfLastUpdate(LocalDateTime.now());
        userRepository.save(user);

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {

        UserEntity user = getUserById(id);

        userMapper.fillFromInDTO(user, userDTO);

        user.setDateOfLastUpdate(LocalDateTime.now());
        userRepository.save(user);

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public UserDTO banUser(Long id, boolean verdict) {
        UserEntity user = getUserById(id);

        if (verdict) {

            if (user.getRoles().contains(userRolesService.getByName("ADMIN"))) {
                throw new InvalidBanException("ADMIN_CANNOT_BAN_HIMSELF");
            }

            user.setBannedDate(LocalDateTime.now());
            userRepository.save(user);

        } else {
            user.setBannedDate(null);
            userRepository.save(user);
        }

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public void confirmUser(Long id) {
        UserEntity user = getUserById(id);

        user.setDateOfApproved(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UserCredentialsReqDTO userChangeCredentialsReqDTO) {

        UserEntity user = getUserById(id);

        if (Objects.isNull(userChangeCredentialsReqDTO.getLogin())
                || Objects.isNull(userChangeCredentialsReqDTO.getNewPassword())
                || Objects.isNull(userChangeCredentialsReqDTO.getOldPassword())
                || Objects.isNull(userChangeCredentialsReqDTO.getConfirmedPassword())
                || (user.getPassword()).equals(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getOldPassword()))
                || !userChangeCredentialsReqDTO.getNewPassword().equals(userChangeCredentialsReqDTO.getConfirmedPassword())) {
            throw new BadCredentialsException(BAD_CREDENTIALS_MESSAGE);
        }

        user.setPassword(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void updateAvatar(Long id, MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "exception.file_is_empty");
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("content-type", image.getContentType());

        //generate
        String path = String.format("%s/%s/%s", BucketName.FILE_IMAGE.getBucketName(), id, "images");
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setName(image.getOriginalFilename());
        userPhoto.setStatus(DocumentStatus.TRANSIENT);
        userPhoto.setPath(path);
        userPhoto.setUserId(id);
        userPhoto.setSize(image.getSize());
        UserPhoto preSavedFile = userPhotoRepository.save(userPhoto);
        String key = String.format("%s", userPhoto.getUniqueId() + "_" + image.getOriginalFilename());

        PutObjectResult uploadedResult = s3Service.upload(path, key, Optional.of(metadata), image.getInputStream());

        UserPhoto uploadedFile = userPhotoRepository.getOne(userPhoto.getId());
        uploadedFile.setStatus(DocumentStatus.COMPLETED);
        uploadedFile.setExternalKey(key);

        UserPhoto savedFile = userPhotoRepository.save(uploadedFile);
    }

    @Override
    @Transactional
    public void updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO) {
        UserEntity user = getUserById(id);

        if (Objects.isNull(userRolesReqDTO) || userRolesReqDTO.getRoles().isEmpty()) {
            throw new ResourceNotFoundException("INVALID_USER_ROLE");
        }

        Set<UserRole> roles = new HashSet<>();

        for (UserRoleDTO role : userRolesReqDTO.getRoles()) {
            UserRole userRole = userRolesService.getByName(role.getRole());
            roles.add(userRole);
        }

        user.setRoles(roles);
        user.setDateOfLastUpdate(LocalDateTime.now());
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO) {
        UserEntity user = userRepository.findByLogin(userPasswordRecoveryReqDTO.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE));

        if (!userPasswordRecoveryReqDTO.getNewPassword().equals(userPasswordRecoveryReqDTO.getConfirmedPassword())
                && userPasswordRecoveryReqDTO.getNewPassword() != null) {
            throw new BadCredentialsException(BAD_CREDENTIALS_MESSAGE);
        }

        user.setPassword(bCryptPasswordEncoder.encode(userPasswordRecoveryReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);

    }

    private UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

}
