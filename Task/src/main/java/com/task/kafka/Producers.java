package com.task.kafka;


import io.confluent.developer.avro.EmailDetails;
import io.confluent.developer.avro.PushNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public record Producers(KafkaTemplate<String, Object> kafkaTemplate) {

    public void isEmailSent(EmailDetails emailDetails) {
        AtomicBoolean isEmailSent = new AtomicBoolean(false);
        CompletableFuture<SendResult<String, Object>> resultCompletableFuture = kafkaTemplate.send("todolist_emails", emailDetails);
        resultCompletableFuture.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                log.debug("Error while sending email", throwable);
            }else {
                log.info("Email sent successfully to TOPIC {}", sendResult.getRecordMetadata().topic());
            }
        });
    }

    public void sendPushNotification(PushNotification pushNotification, String userId) {
        CompletableFuture<SendResult<String, Object>> resultCompletableFuture = kafkaTemplate.send("todolist_pushes", userId, pushNotification);
        resultCompletableFuture.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                log.debug("Error while sending push notification", throwable);
            }else
                log.info("Push notification sent successfully to TOPIC {}", sendResult.getRecordMetadata().topic());
        });
    }
}
