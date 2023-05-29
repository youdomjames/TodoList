//package com.notification.service.push;
//
//import com.notification.dto.PushNotification;
//import com.notification.model.Notification;
//import com.notification.repository.NotificationRepository;
//import com.notification.service.user.UserService;
//import com.notification.util.exceptions.ObjectNotFoundException;
//import com.notification.util.exceptions.PersistenceException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//@Service(value = "pushService")
//public record PushServiceImpl (UserService userService, NotificationRepository repository)  implements PushService{
//    @Override
//    public void send(PushNotification pushNotification, String userId) {
//        if (!userService.userExists(userId)) {
//            throw new ObjectNotFoundException("User not found");
//        }
//        Notification notification = repository.findByUserId(userId).orElse(new Notification());
//
//        pushNotification.setCode(UUID.randomUUID().toString());
//        pushNotification.setActive(true);
//
//        if (notification.getNotifications().size() >= 10) {
//            notification.getNotifications().pop();
//        }
//        notification.getNotifications().push(pushNotification);
//
//        if (!notification.getNotifications().contains(pushNotification)) {
//            throw new PersistenceException("Push notification not saved");
//        }
//    }
//
//    @Override
//    public Set<PushNotification> getPushNotifications(String userId) {
//        return new HashSet<>(repository.findByUserId(userId).orElseThrow(() -> new ObjectNotFoundException("User not found")).getNotifications());
//    }
//}