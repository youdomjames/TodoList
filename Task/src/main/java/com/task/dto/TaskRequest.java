package com.task.dto;

import com.task.util.enums.Priority;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class TaskRequest {
    @NotEmpty
    private LocalDateTime taskDateAndTime;
    @NotNull
    @NotEmpty
    private String taskTitle;
    private Priority taskPriority;
    private String taskDescription;
    private Set<Attendant> invitedAttendants;
    private boolean isAddToCalendarChecked;
    private boolean isCompleted;
}
