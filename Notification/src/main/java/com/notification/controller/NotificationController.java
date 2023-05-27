package com.notification.controller;

import com.notification.dto.PushNotification;
import com.notification.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/notifications")
public record NotificationController(NotificationService notificationService) {
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Set<PushNotification> getNotifications(@RequestParam String userId) {
        return notificationService.getPushNotifications(userId);
    }
}
