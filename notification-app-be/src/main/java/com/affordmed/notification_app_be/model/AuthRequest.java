package com.affordmed.notification_app_be.model;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;
    private String name;
    private String rollNo;
    private String accessCode;
    private String clientID;
    private String clientSecret;

}