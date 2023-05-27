package com.notification.kafka;

import com.notification.service.NotificationService;

public record KafkaTasksDispatch(NotificationService notificationService) {
    public void dispatch() {
//        notificationService.send()
    }
}
