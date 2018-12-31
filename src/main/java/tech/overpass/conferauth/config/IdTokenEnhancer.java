package tech.overpass.conferauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import tech.overpass.conferauth.model.db.User;
import tech.overpass.conferauth.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class IdTokenEnhancer implements TokenEnhancer {

    private final UserService userService;

    @Autowired
    public IdTokenEnhancer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        org.springframework.security.core.userdetails.User user
                = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User fullUser = userService.findByEmail(user.getUsername());
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("userId", fullUser.getId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
