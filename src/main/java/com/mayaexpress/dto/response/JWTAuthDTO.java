package com.mayaexpress.dto.response;

public class JWTAuthDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthDTO(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
