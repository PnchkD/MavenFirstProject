package by.iba.oauth2.user;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("firstName");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("lastName");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}