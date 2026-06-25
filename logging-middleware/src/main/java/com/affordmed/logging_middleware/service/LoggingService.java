package com.affordmed.logging_middleware.service;

import com.affordmed.logging_middleware.dto.LogRequest;
import com.affordmed.logging_middleware.dto.LogResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoggingService {

    public LogResponse log(LogRequest request) {

        request.setTimestamp(LocalDateTime.now().toString());

        System.out.println("========== LOG ==========");
        System.out.println("Level    : " + request.getLevel());
        System.out.println("Service  : " + request.getService());
        System.out.println("Message  : " + request.getMessage());
        System.out.println("Time     : " + request.getTimestamp());
        System.out.println("=========================");

        LogResponse response = new LogResponse();
        response.setSuccess(true);
        response.setMessage("Log saved successfully");

        return response;
    }
}