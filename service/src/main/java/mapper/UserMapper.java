package mapper;

import dto.UserDTO;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDTO userIntoDTO(UserEntity user) {
        return modelMapper.map(user, UserDTO.class);
    }

}
