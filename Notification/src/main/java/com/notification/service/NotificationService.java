package com.notification.service;

import com.notification.dto.EmailDetails;
import com.notification.dto.PushNotification;
import com.notification.model.Notification;
import com.notification.repository.NotificationRepository;
import com.notification.service.user.UserService;
import com.notification.util.exceptions.ObjectNotFoundException;
import com.notification.util.exceptions.PersistenceException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public record NotificationService(NotificationRepository repository, UserService userService){

    private final static String SENDGRID_API_KEY = System.getenv("SENDGRID_API_KEY");
    private final static String templateId = "d-7d319347c4a94f28800e16d82b8f2fe3";

    public Response send(EmailDetails emailDetails) {
        Email from = new Email(System.getenv("SENDER_EMAIL"));
        Email to = new Email(emailDetails.getReceiverEmail());

        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId(templateId);

        Personalization personalization = new Personalization();

        Arrays.stream(emailDetails.getClass().getDeclaredFields()).parallel().forEach(field -> {
            field.setAccessible(true);
            try {
                personalization.addDynamicTemplateData(field.getName(), field.get(emailDetails));
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                field.setAccessible(false);
                log.error("Field " + field.getName() + " is not accessible and not added to dynamic template");
                log.debug(e.getMessage(), e.getCause());
            }
        });

        personalization.addTo(to);
        mail.addPersonalization(personalization);
        SendGrid sendGrid = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        Response response = null;
        try {
            request.setBody(mail.build());
            response = sendGrid.api(request);
            log.info("SENDER: " + emailDetails.getSenderFirstName() + " " + emailDetails.getSenderLastName());
        } catch (IOException ex) {
            log.debug(ex.getMessage(), ex.getCause());
        }
        return response;
    }

    public void send(PushNotification pushNotification, String userId) {
        if (!userService.userExists(userId)) {
            throw new ObjectNotFoundException("User not found");
        }
        Notification notification = repository.findByUserId(userId).orElse(new Notification());

        pushNotification.setCode(UUID.randomUUID().toString());
        pushNotification.setActive(true);

        if (notification.getPushNotifications().size() >= 50) {
            notification.getPushNotifications().pop();
        }
        notification.getPushNotifications().push(pushNotification);

        if (!repository.save(notification).getPushNotifications().contains(pushNotification)) {
            throw new PersistenceException("Push notification not saved");
        }
    }

    public Set<PushNotification> getPushNotifications(String userId) {
        return new HashSet<>(repository.findByUserId(userId).orElseThrow(() -> new ObjectNotFoundException("User not found")).getPushNotifications());
    }
}
