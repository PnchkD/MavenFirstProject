package by.iba.oauth2.user;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("firstName");
    }

    public String getLastName() {
        return (String) attributes.get("lastName");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

}
