package com.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {

    private String receiverEmail;
    private String senderEmail;
    private String receiverFirstName;
    private String receiverLastName;
    private String senderFirstName;
    private String senderLastName;
    private String title;
    private String description;
    private String date;
    private String time;
}
