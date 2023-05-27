package com.notification.service.email;

import com.notification.dto.EmailDetails;
import com.sendgrid.Response;

public interface EmailService{
    Response send(EmailDetails emailDetails);
}
