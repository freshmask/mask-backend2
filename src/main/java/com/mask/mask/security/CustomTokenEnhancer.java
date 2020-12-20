package com.mask.mask.security;

import com.mask.mask.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    UsersServiceAuth usersServiceAuth;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Users user = usersServiceAuth.getUser(authentication.getName());
        Map<String, Object> additionalInfo = new HashMap<>();
//        additionalInfo.put("organization", authentication.getName());
        additionalInfo.put("user", user);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
