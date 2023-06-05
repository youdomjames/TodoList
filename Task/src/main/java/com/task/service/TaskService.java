package com.task.service;

import com.task.dto.*;
import com.task.kafka.Producers;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import com.task.util.exception.ObjectDeletionException;
import com.task.util.exception.ObjectNotFoundException;
import com.task.util.mapper.TaskMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public record TaskService(TaskRepository taskRepository, UserService userService, TaskMapper taskMapper, Producers producers) {

    public TaskResponse getTask(String taskId) {
        return taskMapper.tastToTaskResponse(taskRepository.findById(taskId).orElseThrow(() -> new ObjectNotFoundException("Task not found")));
    }
    public Page<TaskResponse> getTasks(String filter, int page, int size, String sortingDirection, String ...sortingFields) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.valueOf(sortingDirection), sortingFields));
        return taskRepository.findAll(filter, pageable).map(taskMapper::tastToTaskResponse);
    }
    public TaskResponse createTask(TaskRequest taskRequest, String userId) {

        Set<Attendant> attendants = new HashSet<>();
        Optional<User> taskCreatorUser = userService.getUserById(userId);
        //todo: check if task creator is the same as connected user
        if (!taskRequest.getInvitedAttendants().isEmpty() && taskCreatorUser.isPresent()) {
            attendants = taskRequest.getInvitedAttendants().stream().parallel().map(potentialAttendant -> {
                Optional<User> invitationReceiver = userService.getUserById(potentialAttendant.getUserId());
                if (invitationReceiver.isPresent()) {
                    inviteNewAttendant(taskRequest, taskCreatorUser.get(), invitationReceiver.get());
                    potentialAttendant.setEmailSent(true);
                    return potentialAttendant;
                }
                return null;
            }).collect(Collectors.toSet());
        }

        Task task = Task.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .taskDateAndTime(taskRequest.getTaskDateAndTime())
                .taskTitle(taskRequest.getTaskTitle())
                .taskPriority(taskRequest.getTaskPriority())
                .taskDescription(taskRequest.getTaskDescription())
                .invitedAttendants(attendants)
                .isCompleted(false)
                .isAddedToCalendar(taskRequest.isAddToCalendarChecked())
                .build();
        System.out.println("**********Task created: " + taskMapper.tastToTaskResponse(task));
        return taskMapper.tastToTaskResponse(taskRepository.save(task));
    }
    private void inviteNewAttendant(TaskRequest taskRequest, User sender, User receiver) {
        io.confluent.developer.avro.EmailDetails emailDetails = io.confluent.developer.avro.EmailDetails.newBuilder()
                .setTime(taskRequest.getTaskDateAndTime().toLocalTime().toString())
                .setDate(taskRequest.getTaskDateAndTime().toLocalDate().toString())
                .setDescription(taskRequest.getTaskDescription())
                .setReceiverEmail(receiver.getEmail())
                .setSenderEmail(sender.getEmail())
                .setReceiverFirstName(receiver.getFirstName())
                .setReceiverLastName(receiver.getLastName())
                .setSenderFirstName(sender.getFirstName())
                .setSenderLastName(sender.getLastName())
                .setTitle(taskRequest.getTaskTitle())
                .build();
        producers.isEmailSent(emailDetails);
    }
    public List<Task> getDailyTasks() {
        return taskRepository.findAll().stream()
                .parallel()
                .filter(task -> task.getTaskDateAndTime().toLocalDate().equals(LocalDate.now()))
                .toList();
    }
    public TaskResponse updateTask(String taskId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ObjectNotFoundException("Task " + taskId + " not found"));
        return taskMapper.tastToTaskResponse(taskRepository.save(taskMapper.updateUser(taskRequest, task)));
    }
    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        if (taskRepository.existsById(taskId)) {
            throw new ObjectDeletionException("Task " + taskId + " not deleted");
        }
    }
}
