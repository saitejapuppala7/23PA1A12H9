package com.affordmed.notification_app_be.service;

import com.affordmed.notification_app_be.model.Notification;
import com.affordmed.notification_app_be.model.NotificationResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class NotificationService {

    private final RestTemplate restTemplate;
    private final AuthService authService;

    public NotificationService(RestTemplate restTemplate,
                               AuthService authService) {
        this.restTemplate = restTemplate;
        this.authService = authService;
    }

    public List<Notification> getTopNotifications() {

        String token = authService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<NotificationResponse> response =
                restTemplate.exchange(
                        "http://4.224.186.213/evaluation-service/notifications",
                        HttpMethod.GET,
                        entity,
                        NotificationResponse.class
                );

        List<Notification> notifications =
                response.getBody().getNotifications();

        notifications.sort(
                Comparator
                        .comparingInt((Notification n) -> getWeight(n.getType()))
                        .reversed()
                        .thenComparing(
                                n -> LocalDateTime.parse(
                                        n.getTimestamp(),
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                ),
                                Comparator.reverseOrder()
                        )
        );

        return notifications.stream()
                .limit(10)
                .toList();
    }

    private int getWeight(String type) {

        switch (type.toLowerCase()) {
            case "placement":
                return 3;

            case "result":
                return 2;

            case "event":
                return 1;

            default:
                return 0;
        }
    }
}