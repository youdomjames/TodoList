package com.todo.service;

import com.todo.dto.Attendant;
import com.todo.dto.TaskRequest;
import com.todo.dto.TaskResponse;
import com.todo.model.Task;
import com.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public record TaskService (TaskRepository taskRepository, UserService userService) {

    public TaskResponse createTask(TaskRequest taskRequest) {
        Set<Attendant> attendants = new HashSet<>();
        if (!taskRequest.getAttendantUserIds().isEmpty()) {
            attendants = taskRequest.getAttendantUserIds().stream().map(userId -> {
                if (userService.userExists(userId)) {
                    return Attendant.builder().userId(userId).isEmailSent(false).isInvitationAccepted(false).build();
                }
                return null;
            }).collect(Collectors.toSet());
        }
        return null;
    }
}
