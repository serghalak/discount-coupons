package com.exadel.sandbox.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@Getter
@Setter
@NoArgsConstructor
public class JwtConfig {

    @Value("${jwt.signing.key}")
    private String secretKey;
    @Value("${application.jwt.tokenPrefix}")
    private String tokenPrefix;
    @Value("${application.jwt.tokenExpirationAfterDays}")
    private Integer tokenExpirationAfterDays;
    @Value("${application.security.header.username}")
    private String headerUsername;
    @Value("${application.security.header.password}")
    private String headerPassword;
    @Value("${application.security.url.login}")
    private String urlLogin;

    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}
