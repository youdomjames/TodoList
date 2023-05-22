package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String userId;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String email;
    String phoneNumber;
}
