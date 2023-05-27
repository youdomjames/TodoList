package com.notification.service.push;

import com.notification.dto.PushNotification;
import com.sendgrid.Response;

import java.util.Set;

public interface PushService {
    void send(PushNotification pushNotification, String userId);
    Set<PushNotification> getPushNotifications(String userId);
}
