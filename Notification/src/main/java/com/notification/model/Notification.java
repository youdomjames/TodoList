package com.notification.model;

import com.notification.dto.PushNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayDeque;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;
    private String userId;
    @Builder.Default
    private ArrayDeque<PushNotification> pushNotifications = new ArrayDeque<>();
}
