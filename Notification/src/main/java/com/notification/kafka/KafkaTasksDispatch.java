package com.notification.kafka;


import com.notification.service.NotificationService;
import com.sendgrid.Response;
import io.confluent.developer.avro.EmailDetails;
import io.confluent.developer.avro.PushNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public record KafkaTasksDispatch(NotificationService notificationService) {

    @KafkaListener(id="emailNotification", topics = {"todolist_emails"})
    public void sendEmail(EmailDetails emailDetails) {
        com.notification.dto.EmailDetails email = com.notification.dto.EmailDetails.builder()
                .receiverEmail(String.valueOf(emailDetails.getReceiverEmail()))
                .senderEmail(String.valueOf(emailDetails.getSenderEmail()))
                .receiverFirstName(String.valueOf(emailDetails.getReceiverFirstName()))
                .receiverLastName(String.valueOf(emailDetails.getReceiverLastName()))
                .date(String.valueOf(emailDetails.getDate()))
                .receiverFirstName(String.valueOf(emailDetails.getReceiverFirstName()))
                .receiverLastName(String.valueOf(emailDetails.getReceiverLastName()))
                .senderFirstName(String.valueOf(emailDetails.getSenderFirstName()))
                .senderLastName(String.valueOf(emailDetails.getSenderLastName()))
                .time(String.valueOf(emailDetails.getTime()))
                .title(String.valueOf(emailDetails.getTitle()))
                .description(String.valueOf(emailDetails.getDescription()))
                .build();
        Response response = notificationService.send(email);
        if (response.getStatusCode() != 200) {
            log.error("Email not sent, STATUS=: {}", response.getStatusCode());
            log.debug(response.getBody());
        }else
            log.info("Email sent");
    }

    @KafkaListener(id="pushNotification", topics = {"todolist_pushes"})
    public void sendPush(ConsumerRecord<String, PushNotification> record) {
        String userId = record.key();
        PushNotification pushNotification = record.value();
        com.notification.dto.PushNotification push = com.notification.dto.PushNotification.builder()
                .title(String.valueOf(pushNotification.getTitle()))
                .message(String.valueOf(pushNotification.getMessage()))
                .build();
        notificationService.send(push, userId);
        log.info("Push notification sent");
    }
}
