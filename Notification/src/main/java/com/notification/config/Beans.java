package com.notification.config;

import com.notification.service.NotificationService;
import com.notification.service.email.EmailServiceImpl;
import com.notification.service.push.PushServiceImpl;
import jakarta.inject.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    public NotificationService notificationService(){
        return (NotificationService) new EmailServiceImpl();
    }

//    @Qualifier("pushService")
//    public NotificationService notificationService(){
//        return (NotificationService) new PushServiceImpl();
//    }
}
