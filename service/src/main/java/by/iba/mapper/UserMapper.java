package by.iba.mapper;

import by.iba.dto.in.UserChangeAvatarInDTO;
import by.iba.dto.in.UserChangeCredentialsInDTO;
import by.iba.dto.in.UserChangingInDTO;
import by.iba.dto.out.UserDTO;
import by.iba.entity.user.Photo;
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

    public UserDTO userIntoDTO(UserEntity user) {
        return modelMapper.map(user, UserDTO.class);
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

}
