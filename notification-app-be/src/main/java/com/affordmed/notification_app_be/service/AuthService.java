package com.affordmed.notification_app_be.service;

import com.affordmed.notification_app_be.model.AuthRequest;
import com.affordmed.notification_app_be.model.AuthResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {

        String url = "http://4.224.186.213/evaluation-service/auth";

        AuthRequest request = new AuthRequest();

        request.setEmail("23pa1a12h9@vishnu.edu.in");
        request.setName("Sai Teja");
        request.setRollNo("23pa1a12h9");
        request.setAccessCode("ahXjvp");
        request.setClientID("181b60fe-b7c8-4185-baf3-cd632c773926");
        request.setClientSecret("EEbuaxHBBzSxMVKA");

        AuthResponse response =
                restTemplate.postForObject(url, request, AuthResponse.class);

        return response.getAccess_token();
    }
}