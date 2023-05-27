package com.notification.service;

import com.notification.service.email.EmailService;
import com.notification.service.push.PushService;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService extends EmailService, PushService {
}
