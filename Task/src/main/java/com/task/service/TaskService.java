package com.task.service;

import com.task.dto.Attendant;
import com.task.dto.TaskRequest;
import com.task.dto.TaskResponse;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import com.task.util.exception.ObjectDeletionException;
import com.task.util.exception.ObjectNotFound;
import com.task.util.mapper.TaskMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public record TaskService(TaskRepository taskRepository, UserService userService, TaskMapper taskMapper) {

    public TaskResponse getTask(String taskId) {
        return taskMapper.mapToTaskResponse(taskRepository.findById(taskId).orElseThrow(() -> new ObjectNotFound("Task not found")));
    }
    public Page<TaskResponse> getTasks(String filter, int page, int size, String sortingDirection, String ...sortingFields) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.valueOf(sortingDirection), sortingFields));
        return taskRepository.findAll(filter, pageable).map(taskMapper::mapToTaskResponse);
    }
    public TaskResponse createTask(TaskRequest taskRequest) {
        Set<Attendant> attendants = new HashSet<>();
        if (!taskRequest.getInvitedAttendants().isEmpty()) {
            attendants = taskRequest.getInvitedAttendants().stream().parallel().map(potentialAttendant -> {
                if (userService.userExists(potentialAttendant.getUserId())) {
                    return Attendant.builder().userId(potentialAttendant.getUserId()).isEmailSent(false).isInvitationAccepted(false).build();
                }
                return null;
            }).collect(Collectors.toSet());
        }

        boolean isInvitationEmailSent = false;
        if (taskRequest.isAddToCalendarChecked()) {
            isInvitationEmailSent = isEmailSent(taskRequest);
        }

        Task task = Task.builder()
                .id(UUID.randomUUID().toString())
                .taskDateAndTime(taskRequest.getTaskDateAndTime())
                .taskTitle(taskRequest.getTaskTitle())
                .taskPriority(taskRequest.getTaskPriority())
                .taskDescription(taskRequest.getTaskDescription())
                .invitedAttendants(attendants)
                .isCompleted(false)
                .isAddedToCalendar(isInvitationEmailSent)
                .build();
        taskRepository.save(task);
        return taskMapper.mapToTaskResponse(task);
    }

    public TaskResponse updateTask(String taskId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ObjectNotFound("Task " + taskId + " not found"));
        return taskMapper.mapToTaskResponse(taskRepository.save(taskMapper.updateUser(taskRequest, task)));
    }

    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        if (taskRepository.existsById(taskId)) {
            throw new ObjectDeletionException("Task " + taskId + " not deleted");
        }
    }

    //TODO: Implement isEmailSent()
    private boolean isEmailSent(TaskRequest taskRequest) {
        return false;
    }
}
