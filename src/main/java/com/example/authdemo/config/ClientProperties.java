package com.example.authdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "client.auth-server")
public class ClientProperties {
    private String clientId;
    private String clientSecret;
    private String userAuthorizationUri;
    private String accessTokenUri;
    private String directUri;
}
