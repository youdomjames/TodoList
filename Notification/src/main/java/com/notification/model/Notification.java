package com.notification.model;

import com.notification.dto.PushNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String id;
    private String notifiedUserId;
    @Builder.Default
    private ArrayDeque<PushNotification> notifications = new ArrayDeque<>();
}
