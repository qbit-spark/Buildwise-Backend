package com.qbitspark.buildwisebackend.authentication_service.payloads;

import lombok.Data;

@Data
public class LoginResponse {
    private Object userData;
    private String accessToken;
    private String refreshToken;
}
