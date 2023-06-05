package com.task.controller;

import com.task.dto.TaskRequest;
import com.task.dto.TaskResponse;
import com.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public record TaskController(TaskService taskService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TaskResponse> getTasks(@RequestParam String filter, int page, int size,
                                       String sortingDirection, @RequestParam(name = "sortingField") String ...sortingFields) {
        return taskService.getTasks(filter, page, size, sortingDirection, sortingFields);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse getTaskById(@PathVariable String id) {
        return taskService.getTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest, @RequestParam String userId) {
        return taskService.createTask(taskRequest, userId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTask(@PathVariable String id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
