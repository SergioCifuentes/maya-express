package com.mayaexpress.dto.response;

import lombok.Getter;

@Getter
public class JWTAuthDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    private Object role;

    public JWTAuthDTO(String accessToken, Object role) {
        this.accessToken = accessToken;
        this.role=role;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
