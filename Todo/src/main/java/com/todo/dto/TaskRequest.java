package com.todo.dto;

import com.todo.util.enums.Priority;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class TaskRequest {
    private LocalDateTime taskDateAndTime;
    private String taskTitle;
    private Priority taskPriority;
    private String taskDescription;
    private Set<String> attendantUserIds;
    private boolean isAddToCalendarChecked;
}
