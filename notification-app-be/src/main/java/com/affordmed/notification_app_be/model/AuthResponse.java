package com.affordmed.notification_app_be.model;

import lombok.Data;

@Data
public class AuthResponse {

    private String token_type;
    private String access_token;
    private long expires_in;

}