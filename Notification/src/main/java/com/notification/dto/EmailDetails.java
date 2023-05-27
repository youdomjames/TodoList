package com.notification.dto;

import lombok.Data;

@Data
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
