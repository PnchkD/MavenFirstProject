package by.iba.mapper;

import by.iba.dto.req.user.UserAvatarReqDTO;
import by.iba.dto.req.user.UserPersonalDataReqDTO;
import by.iba.dto.req.user.UserReqDTO;
import by.iba.dto.resp.PhotoForAmazonResp;
import by.iba.dto.resp.user.UserDTO;
import by.iba.dto.resp.user.UserRoleDTO;
import by.iba.entity.photo.Photo;
import by.iba.entity.photo.UserPhoto;
import by.iba.entity.user.UserEntity;
import by.iba.entity.user.UserRole;
import by.iba.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserPhotoMapper userPhotoMapper;

    public UserDTO fillFromInDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();

        if(Objects.nonNull(user.getId())) {
            userDTO.setId(user.getId());
        }

        if(Objects.nonNull(user.getEmail())) {
            userDTO.setEmail(user.getEmail());
        }

        if(Objects.nonNull(user.getFirstName())) {
            userDTO.setFirstName(user.getFirstName());
        }

        if(Objects.nonNull(user.getLastName())) {
            userDTO.setLastName(user.getLastName());
        }

        if(!user.getRoles().isEmpty()) {
            userDTO.setRoles(user.getRoles()
                    .stream()
                    .map(x -> new UserRoleDTO(x.getName()))
                    .collect(Collectors.toList()));
        }

        if(Objects.nonNull(user.getBannedDate())) {
            userDTO.setBannedDate(user.getBannedDate());
        }

        if(Objects.nonNull(user.getDateOfApproved())) {
            userDTO.setDateOfApproved(user.getDateOfApproved());
        }

        if(Objects.nonNull(user.getIsMailConfirmed())) {
            userDTO.setIsMailConfirmed(user.getIsMailConfirmed());
        }

        if(Objects.nonNull(user.getDateOfLastLogin())) {
            userDTO.setDateOfLastLogin(user.getDateOfLastLogin());
        }

        if(Objects.nonNull(user.getDateOfCreation())) {
            userDTO.setDateOfCreation(user.getDateOfCreation());
        }

        if(Objects.nonNull(user.getDateOfLastUpdate())) {
            userDTO.setDateOfLastUpdate(user.getDateOfLastUpdate());
        }

        return userDTO;
    }

    public Optional<UserDTO> fillFromInDTO(Optional<UserEntity> user){
        Optional<UserDTO> userDTO = Optional.of(new UserDTO());
        if(user.isPresent()) {
            if (Objects.nonNull(user.get().getId())) {
                userDTO.get().setId(user.get().getId());
            }

            if (Objects.nonNull(user.get().getEmail())) {
                userDTO.get().setEmail(user.get().getEmail());
            }

            if (Objects.nonNull(user.get().getLogin())) {
                userDTO.get().setLogin(user.get().getLogin());
            }

            if (Objects.nonNull(user.get().getFirstName())) {
                userDTO.get().setFirstName(user.get().getFirstName());
            }

            if (Objects.nonNull(user.get().getLastName())) {
                userDTO.get().setLastName(user.get().getLastName());
            }

            if (!user.get().getRoles().isEmpty()) {
                userDTO.get().setRoles(user.get().getRoles()
                        .stream()
                        .map(x -> new UserRoleDTO(x.getName()))
                        .collect(Collectors.toList()));
            }

            if (Objects.nonNull(user.get().getBannedDate())) {
                userDTO.get().setBannedDate(user.get().getBannedDate());
            }

            if (Objects.nonNull(user.get().getDateOfApproved())) {
                userDTO.get().setDateOfApproved(user.get().getDateOfApproved());
            }

            if (Objects.nonNull(user.get().getIsMailConfirmed())) {
                userDTO.get().setIsMailConfirmed(user.get().getIsMailConfirmed());
            }

            if (Objects.nonNull(user.get().getDateOfLastLogin())) {
                userDTO.get().setDateOfLastLogin(user.get().getDateOfLastLogin());
            }

            if (Objects.nonNull(user.get().getDateOfCreation())) {
                userDTO.get().setDateOfCreation(user.get().getDateOfCreation());
            }

            if (Objects.nonNull(user.get().getDateOfLastUpdate())) {
                userDTO.get().setDateOfLastUpdate(user.get().getDateOfLastUpdate());
            }
        } else {
            throw new ResourceNotFoundException("USER_HAS_BEEN_NOT_FOUND");
        }

        return userDTO;
    }

    public UserDTO fillFromInDTO(UserEntity user, PhotoForAmazonResp userPhoto){
        UserDTO userDTO = new UserDTO();

        if(Objects.nonNull(user.getId())) {
            userDTO.setId(user.getId());
        }

        if(Objects.nonNull(user.getEmail())) {
            userDTO.setEmail(user.getEmail());
        }

        if(Objects.nonNull(user.getFirstName())) {
            userDTO.setFirstName(user.getFirstName());
        }

        if(Objects.nonNull(user.getLastName())) {
            userDTO.setLastName(user.getLastName());
        }

        if(!user.getRoles().isEmpty()) {
            userDTO.setRoles(user.getRoles()
                    .stream()
                    .map(x -> new UserRoleDTO(x.getName()))
                    .collect(Collectors.toList()));
        }

        if(Objects.nonNull(user.getBannedDate())) {
            userDTO.setBannedDate(user.getBannedDate());
        }

        if(Objects.nonNull(user.getDateOfApproved())) {
            userDTO.setDateOfApproved(user.getDateOfApproved());
        }

        if(Objects.nonNull(user.getIsMailConfirmed())) {
            userDTO.setIsMailConfirmed(user.getIsMailConfirmed());
        }

        if(Objects.nonNull(user.getDateOfLastLogin())) {
            userDTO.setDateOfLastLogin(user.getDateOfLastLogin());
        }

        if(Objects.nonNull(userPhoto)) {
            userDTO.setAvatar(userPhoto);
        }

        if(Objects.nonNull(user.getDateOfCreation())) {
            userDTO.setDateOfCreation(user.getDateOfCreation());
        }

        if(Objects.nonNull(user.getDateOfLastUpdate())) {
            userDTO.setDateOfLastUpdate(user.getDateOfLastUpdate());
        }

        return userDTO;
    }

    public void fillFromInDTO(UserEntity user, UserPersonalDataReqDTO userPersonalDataReqDTO){

        if(Objects.nonNull(userPersonalDataReqDTO.getEmail())) {
            user.setEmail(userPersonalDataReqDTO.getEmail());
        }

        if(Objects.nonNull(userPersonalDataReqDTO.getFirstName())) {
            user.setFirstName(userPersonalDataReqDTO.getFirstName());
        }

        if(Objects.nonNull(userPersonalDataReqDTO.getLastName())) {
            user.setLastName(userPersonalDataReqDTO.getLastName());
        }

    }

    public void fillFromInDTO(UserEntity user, UserDTO userDTO){

        if(Objects.nonNull(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }

        if(Objects.nonNull(userDTO.getFirstName())) {
            user.setFirstName(userDTO.getFirstName());
        }

        if(Objects.nonNull(userDTO.getLastName())) {
            user.setLastName(userDTO.getLastName());
        }

    }

    public void fillFromInDTO(UserEntity user, Photo avatar){

        if (Objects.nonNull(avatar)) {
            user.setAvatar(avatar);
        }

    }

    public UserEntity fillFromRespDTO(UserReqDTO userReqDTO){

        UserEntity user = new UserEntity();

        if (Objects.nonNull(userReqDTO.getFirstName())) {
            user.setFirstName(userReqDTO.getFirstName());
        }

        if (Objects.nonNull(userReqDTO.getLastName())) {
            user.setLastName(userReqDTO.getFirstName());
        }

        if (Objects.nonNull(userReqDTO.getLogin())) {
            user.setLogin(userReqDTO.getLogin());
        }

        if (Objects.nonNull(userReqDTO.getEmail())) {
            user.setEmail(userReqDTO.getEmail());
        }

        if (Objects.nonNull(userReqDTO.getPassword())) {
            user.setPassword(userReqDTO.getPassword());
        }

        return user;
    }

}
