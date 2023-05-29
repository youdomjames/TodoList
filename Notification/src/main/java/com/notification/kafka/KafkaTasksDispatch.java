package com.notification.kafka;

import com.notification.dto.EmailDetails;
import com.notification.dto.PushNotification;
import com.notification.service.NotificationService;
import com.sendgrid.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public record KafkaTasksDispatch(NotificationService notificationService) {

    @KafkaListener(id="emailNotification", topics = {"emails"})
    public void sendEmail(EmailDetails emailDetails) {
        Response response = notificationService.send(emailDetails);
        if (response.getStatusCode() != 200) {
            log.error("Email not sent, STATUS=: {}", response.getStatusCode());
            log.debug(response.getBody());
        }
        log.info("Email sent");
    }

    @KafkaListener(id="pushNotification", topics = {"pushes"})
    public void sendPush(Map.Entry<String, PushNotification> payload) {
        String userId = payload.getKey();
        PushNotification pushNotification = payload.getValue();
        notificationService.send(pushNotification, userId);
        log.info("Push notification sent");
    }
}
