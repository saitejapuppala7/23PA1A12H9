package com.affordmed.notification_app_be.controller;

import com.affordmed.notification_app_be.model.Notification;
import com.affordmed.notification_app_be.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications/top10")
    public List<Notification> getTopNotifications() {
        return notificationService.getTopNotifications();
    }
}
