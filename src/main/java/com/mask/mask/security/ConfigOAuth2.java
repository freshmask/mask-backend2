package com.mask.mask.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter {

    private String clientId = "maskapp";
    private String clientSecret = "maskapp-secret-key";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEAudM4tuqs0qc6ABtBhlDBacHe1hzU5mfLxuAxX0MWT00zCuD6\n" +
            "/Vl+/M1D26PNBr99q2k4/nwxJpNHQ7ip3dJyy//0wMsdCOTAxR5CwmoxhMNCYRLb\n" +
            "yCc/qmBMBgLjWRBnht32Yhjl79v9YPZE1LRV30PfXmK/Bqf3pPaiOBWKkeYt4IcK\n" +
            "sve5fl5eswMV5QOpwxvP7Dvqo+YOoaIcifCxoRqntFd0DfdqPISRhk27qxlC6raH\n" +
            "ym6VaNNUQbChG7MHfBXf66YoBb+vq/UgZC8t2YSzQrwn9OL664yUZt8C17OptDs8\n" +
            "5xOtcwshZ5BRygCZqxouK1eQ2x+tptZm0GXLcwIDAQABAoIBAAkwieh52IweWv7w\n" +
            "WiAzVC1Puh5qlJjEX055fdeRMMRBNBoAoEjxKReFayqsghIoXCWi8X+cUq3usKKH\n" +
            "ulbO9oBiKw9xvADZlzLoWTLWH5bKNTkpvpa6Yiqh0oClmeqopgYEag/V3WEsRu6J\n" +
            "n0PJm0mMSLqZNYs1uUh+Le2KGy012FAWfxpdmogk8/ws3ZUd43NMaA4EgXFZbjuO\n" +
            "XDEC7/bM/PfvOxxZJPwqe6Jca+1/lMxX92NVlaBNIw+K6JlL7OYi0M2vo4eY1Y/S\n" +
            "JHJaEht9g6S56fUVGE4TYPc1qgW08t4kXSHLYbl1sA3ND2Te6jZufO/oXpx4Ymq7\n" +
            "5J88i/kCgYEA4IGG+SUCtnnEWX76EyWjBia42RsNSfT/OkGsAzMNxULZkgHyhRz5\n" +
            "CuW2H4hfnhNhxqR8XwS8UfacHiH/8JK1e5/M0+PNl7r0nhGoTM/F+tlydRVD+DNz\n" +
            "REzNYcywtlVP9jIkb0Ipt5kUOVSc6QUIUvkvOULCQL/tPnoLwP4iFQcCgYEA0+SV\n" +
            "f6y04bR4NAhyFXgX2K0QeQjnQ+JA/OWP6JMQqMZxcZDjO0bpkn+vazYWD4oC+Xnn\n" +
            "ZqzROthx3vJGSBjJPahVc/BuOpJT3QYkXQ8J0xCH2GwQGvtoz5EbX5Q0/pkPsQ1n\n" +
            "0sQS1ct6JNstIlFOmnlOnEC1qDDC1UwL3RpJxzUCgYEAsBvLFgs7SorQRGf/oR9n\n" +
            "935OiDfZP8eIolk7prLYvYGIDCms5VBp52t0Zt3iK60GiP0kdqDPXPg3VMfU7hEX\n" +
            "fadH1HshHkHQe2JchAyEQBds2n1Y+QzbAZvBjFM114GqnPSOchwTf2r8W1CNWZjS\n" +
            "A8xmh+NfX81uPBEewUBY2ecCgYEAoCpOnt1rNJh3q80efv/x9oSKyRJjzTo+qWrU\n" +
            "ppZTp1+o7JD6Uho9/9CoRj/x2PkXWoA8262R0gs9t6omWeTyaLrPLP32NCvbXFyC\n" +
            "LB1r7bC3Hm5AlkSnaw5iRJMAo9oKtGhPrE8tdZGC2E0cBq6y3a0IMK/xIBsLirMa\n" +
            "3Jw8XGkCgYEA0sCEiH/Jxd602mkm0l9i/XEW96H013KEvemxGvbTo5M+IukQwHzU\n" +
            "YgxuVBFLW5pHGBkyShRu8jowMrbm80irhAketFcn9z7wUJkuHPkOY83RlR3SqLYb\n" +
            "skPa9PDyL16rfRzyGoSrniTolvau0W99Ajao02m6rZq+fwY3hCw2Cb4=\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAudM4tuqs0qc6ABtBhlDB\n" +
            "acHe1hzU5mfLxuAxX0MWT00zCuD6/Vl+/M1D26PNBr99q2k4/nwxJpNHQ7ip3dJy\n" +
            "y//0wMsdCOTAxR5CwmoxhMNCYRLbyCc/qmBMBgLjWRBnht32Yhjl79v9YPZE1LRV\n" +
            "30PfXmK/Bqf3pPaiOBWKkeYt4IcKsve5fl5eswMV5QOpwxvP7Dvqo+YOoaIcifCx\n" +
            "oRqntFd0DfdqPISRhk27qxlC6raHym6VaNNUQbChG7MHfBXf66YoBb+vq/UgZC8t\n" +
            "2YSzQrwn9OL664yUZt8C17OptDs85xOtcwshZ5BRygCZqxouK1eQ2x+tptZm0GXL\n" +
            "cwIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }


    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
////        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
//        endpoints.authenticationManager(authenticationManager)
//                .tokenStore(tokenStore())
//                .accessTokenConverter(accessTokenConverter());
////                .tokenEnhancer(tokenEnhancer());
//    }

    @Override
    public void configure(
            AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}
