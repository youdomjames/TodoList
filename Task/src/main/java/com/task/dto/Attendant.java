package com.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendant {
    private String userId;
    private boolean isEmailSent;
    private boolean isInvitationAccepted;
}
