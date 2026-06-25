package com.affordmed.logging_middleware.controller;

import com.affordmed.logging_middleware.dto.LogRequest;
import com.affordmed.logging_middleware.dto.LogResponse;
import com.affordmed.logging_middleware.service.LoggingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LoggingController {

    private final LoggingService loggingService;

    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @PostMapping
    public LogResponse createLog(@RequestBody LogRequest request) {
        return loggingService.log(request);
    }
}