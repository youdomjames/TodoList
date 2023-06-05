package com.task.model;

import com.task.dto.Attendant;
import com.task.util.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String id;
    private String userId;
    private LocalDateTime taskDateAndTime;
    private String taskTitle;
    private Priority taskPriority;
    private String taskDescription;
    private Set<Attendant> invitedAttendants;
    private boolean isCompleted;
    private boolean isAddedToCalendar;
}
