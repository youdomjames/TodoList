package com.notification.service.email;

import com.notification.dto.EmailDetails;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class EmailServiceImpl implements EmailService {

    private final static String SENDGRID_API_KEY = System.getenv("SENDGRID_API_KEY");
    private final static String templateId = "d-7d319347c4a94f28800e16d82b8f2fe3";

    @Override
    public Response send(EmailDetails emailDetails) {
        Email from = new Email("youdomjames@gmail.com");
        String subject = emailDetails.getTitle();
        Email to = new Email("youdomjames14@gmail.com");

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
                log.error("Field " + field.getName() + " is not accessible");
                log.debug(e.getMessage(), e.getCause());
            }
        });

        personalization.addTo(to);
        personalization.setSubject(subject);
        mail.addPersonalization(personalization);
        SendGrid sendGrid = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");

        Response response = null;
        try {
            request.setBody(mail.build());
            response = sendGrid.api(request);
        } catch (IOException ex) {
            log.debug(ex.getMessage(), ex.getCause());
        }

        return response;
    }
}
