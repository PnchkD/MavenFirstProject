package by.iba.mapper;

import by.iba.UserRolesService;
import by.iba.dto.req.UserChangeAvatarReqDTO;
import by.iba.dto.req.UserChangeCredentialsReqDTO;
import by.iba.dto.req.UserChangeRoleReqDTO;
import by.iba.dto.req.UserChangePersonalDataReqDTO;
import by.iba.dto.resp.UserDTO;
import by.iba.entity.user.Photo;
import by.iba.entity.user.UserEntity;
import by.iba.entity.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRolesService userRolesService;

    public UserDTO userIntoDTO(UserEntity user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if(Objects.nonNull(user.getAvatar())) {
            userDTO.setAvatar(user.getAvatar().getImageUrl());
        }
        userDTO.setRole(userRolesService.findByUserId(user.getId()).getName());
        return userDTO;
    }

    public void fillFromInDTO(UserEntity user, UserChangePersonalDataReqDTO userChangingInDTO){
        if(Objects.nonNull(userChangingInDTO.getEmail())) {
            user.setEmail(userChangingInDTO.getEmail());
        }

        if(Objects.nonNull(userChangingInDTO.getFirstName())) {
            user.setFirstName(userChangingInDTO.getFirstName());
        }

        if(Objects.nonNull(userChangingInDTO.getLastName())) {
            user.setLastName(userChangingInDTO.getLastName());
        }

    }

    public void fillFromInDTO(UserEntity user, UserChangeCredentialsReqDTO userChangingInDTO) {

        if (Objects.nonNull(userChangingInDTO.getNewPassword())
                && userChangingInDTO.getNewPassword().equals(userChangingInDTO.getConfirmedPassword())
                && !userChangingInDTO.getNewPassword().equals(userChangingInDTO.getOldPassword())) {

            user.setPassword(bCryptPasswordEncoder.encode(userChangingInDTO.getNewPassword()));

        }
    }

    public void fillFromInDTO(UserEntity user, UserChangeAvatarReqDTO userChangeAvatarInDTO) {
        if (Objects.nonNull(userChangeAvatarInDTO.getImage())) {
            user.setAvatar(new Photo(userChangeAvatarInDTO.getImage()));
        }
    }

    public void fillFromInDTO(UserEntity user, UserChangeRoleReqDTO userChangeRoleInDTO) {
        if (Objects.nonNull(userChangeRoleInDTO.getRole())) {
            user.getRoles().add(userRolesService.createRole(userChangeRoleInDTO.getRole(), user.getId()));
        }
    }

}
