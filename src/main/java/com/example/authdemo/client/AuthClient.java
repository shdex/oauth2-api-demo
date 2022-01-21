package com.example.authdemo.client;

import com.example.authdemo.config.ClientProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthClient extends OAuthClient {

    public static final String RESPONSE_TYPE = "code";

    @Autowired
    private ClientProperties properties;

    public AuthClient() {
        super(new URLConnectionClient());
    }

    public String authCodeUrl(){
        OAuthClientRequest request;
        try {
            request = OAuthClientRequest
                    .authorizationLocation(properties.getUserAuthorizationUri())
                    .setClientId(properties.getClientId())
                    .setRedirectURI(properties.getDirectUri())
                    .setResponseType(RESPONSE_TYPE)
                    .buildQueryMessage();
        } catch (OAuthSystemException e) {
            throw new RuntimeException("构建请求参数异常");
        }
        return request.getLocationUri();
    }

    /**
     *  请求token
     * @param code
     * @return
     */
    public String getAccessToken(String code,String directUrl) {
        OAuthAccessTokenResponse oAuthResponse = null;
        log.info(properties.toString());
        log.info(directUrl);
        try {
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(properties.getAccessTokenUri())
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(properties.getClientId())
                    .setClientSecret(properties.getClientSecret())
                    .setCode(code)
                    .setRedirectURI(directUrl)
                    .buildQueryMessage();
            oAuthResponse = accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
        return oAuthResponse.getAccessToken();
    }

}
