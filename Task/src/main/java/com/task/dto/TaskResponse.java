package com.task.dto;

import com.task.util.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private String userId;
    private LocalDateTime taskDateAndTime;
    private String taskTitle;
    private Priority taskPriority;
    private String taskDescription;
    private Set<Attendant> invitedAttendants;
    private boolean isCompleted;
    private boolean isAddedToCalendar;
}
