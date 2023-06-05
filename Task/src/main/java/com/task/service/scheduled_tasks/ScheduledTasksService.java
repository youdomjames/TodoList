package com.task.service.scheduled_tasks;

import com.task.dto.User;
import com.task.kafka.Producers;
import com.task.model.Task;
import com.task.service.TaskService;
import com.task.service.UserService;
import com.task.util.exception.ObjectNotFoundException;
import io.confluent.developer.avro.PushNotification;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
public record ScheduledTasksService(TaskService taskService, Producers producers, UserService userService) {

    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 24)
    public void sendDailyTasksToNotificationService() {
        List<Task> tasks = taskService.getDailyTasks();
        if (!tasks.isEmpty()) {
            tasks.stream().parallel().forEach(task -> {
                PushNotification pushNotification = PushNotification.newBuilder()
                        .setTitle(task.getTaskTitle())
                        .setMessage(buildMessage(task))
                        .build();
                producers.sendPushNotification(pushNotification, task.getUserId());
            });
        }

    }


    private String buildMessage(Task task){
        return String.valueOf(task.getTaskTitle().charAt(0)).toUpperCase() + task.getTaskTitle().substring(1)+ " on " +
                task.getTaskDateAndTime().toLocalDate().toString() + " at " +
                task.getTaskDateAndTime().toLocalTime().toString() + " with "+ task.getInvitedAttendants().stream()
                                .map(attendant ->
                                        buildFullName(userService.getUserById(attendant.getUserId()).orElseThrow(() -> new ObjectNotFoundException("User not found"))))
                                .toList();
    }

    private String buildFullName(User user){
        return user.getFirstName() + " " + user.getLastName();
    }
}
