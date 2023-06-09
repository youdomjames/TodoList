package com.todo.model;

import com.todo.dto.Attendant;
import com.todo.util.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String id;
    private LocalDateTime taskDateAndTime;
    private String taskTitle;
    private Priority taskPriority;
    private String taskDescription;
    private Set<Attendant> invitedAttendants;
    private boolean isCompleted;
    private boolean isAddedToCalendar;
}
