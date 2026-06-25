package com.affordmed.notification_app_be.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Notification {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Timestamp")
    private String timestamp;
}