package by.iba.oauth2;

import by.iba.UserService;
import by.iba.dto.req.user.UserPersonalDataReqDTO;
import by.iba.dto.req.user.UserReqDTO;
import by.iba.dto.resp.user.UserDTO;
import by.iba.entity.SupportedAuthProvider;
import by.iba.entity.user.UserEntity;
import by.iba.exception.OAuth2AuthenticationProcessingException;
import by.iba.mapper.UserMapper;
import by.iba.oauth2.user.OAuth2UserInfo;
import by.iba.oauth2.user.OAuth2UserInfoFactory;
import by.iba.oauth2.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public CustomOAuth2UserService(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw new OAuth2AuthenticationProcessingException(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }
    }


    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes());

        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<UserEntity> userOptional = userService.findByEmail(oAuth2UserInfo.getEmail());

        UserDTO user;

        if (userOptional.isPresent()) {
            user = userMapper.fillFromInDTO(userOptional.get());
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }


    private UserDTO registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        UserReqDTO user = new UserReqDTO();
        user.setProvider(SupportedAuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setEmail(oAuth2UserInfo.getEmail());
        return userService.save(user);
    }

    private UserDTO updateExistingUser(UserDTO existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setFirstName(oAuth2UserInfo.getFirstName());
        existingUser.setLastName(oAuth2UserInfo.getLastName());
        return userService.update(existingUser.getId(), existingUser);
    }

}
