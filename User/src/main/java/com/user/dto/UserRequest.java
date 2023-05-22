package com.user.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
    Date dateOfBirth;
}
