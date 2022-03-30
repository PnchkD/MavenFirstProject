package by.iba.mapper;

import by.iba.dto.in.UserChangeAvatarInDTO;
import by.iba.dto.in.UserChangeCredentialsInDTO;
import by.iba.dto.in.UserChangeRoleInDTO;
import by.iba.dto.in.UserChangingInDTO;
import by.iba.dto.out.UserForAdminOutDTO;
import by.iba.dto.out.UserOutDTO;
import by.iba.entity.user.Photo;
import by.iba.entity.user.Role;
import by.iba.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserOutDTO userIntoDTO(UserEntity user) {
        UserOutDTO userOutDTO = modelMapper.map(user, UserOutDTO.class);
        if(user.getAvatar() != null) {
            userOutDTO.setAvatar(user.getAvatar().getImageUrl());
        }
        return userOutDTO;
    }

    public UserForAdminOutDTO userForAdminOutDTO(UserEntity user) {
        return modelMapper.map(user, UserForAdminOutDTO.class);
    }

    public void fillFromInDTO(UserEntity user, UserChangingInDTO userChangingInDTO){
        if(userChangingInDTO.getEmail() != null) {
            user.setEmail(userChangingInDTO.getEmail());
        }

        if(userChangingInDTO.getFirstName() != null) {
            user.setFirstName(userChangingInDTO.getFirstName());
        }

        if(userChangingInDTO.getLastName() != null) {
            user.setLastName(userChangingInDTO.getLastName());
        }

    }

    public void fillFromInDTO(UserEntity user, UserChangeCredentialsInDTO userChangingInDTO) {
        if (userChangingInDTO.getLogin() != null) {
            user.setLogin(userChangingInDTO.getLogin());
        }

        if (userChangingInDTO.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(userChangingInDTO.getPassword()));
        }
    }

    public void fillFromInDTO(UserEntity user, UserChangeAvatarInDTO userChangeAvatarInDTO) {
        if (userChangeAvatarInDTO.getImage() != null) {
            user.setAvatar(new Photo(userChangeAvatarInDTO.getImage()));
        }
    }

    public void fillFromInDTO(UserEntity user, UserChangeRoleInDTO userChangeRoleInDTO) {
        if (userChangeRoleInDTO.getRole() != null) {
            user.setRole(Role.valueOf(userChangeRoleInDTO.getRole()));
        }
    }

}
