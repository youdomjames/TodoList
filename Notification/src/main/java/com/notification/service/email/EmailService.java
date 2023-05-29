package com.notification.service.email;

import com.notification.dto.EmailDetails;
import com.sendgrid.Response;
import org.springframework.stereotype.Component;

@FunctionalInterface
public interface EmailService{
    Response send(EmailDetails emailDetails);
}
