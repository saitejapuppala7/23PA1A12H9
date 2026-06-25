package com.affordmed.notification_app_be.model;

import lombok.Data;
import java.util.List;

@Data
public class NotificationResponse {

    private List<Notification> notifications;

}