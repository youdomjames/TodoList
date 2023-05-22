package com.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String userId;
    @Column(unique = true)
    String email;
    String firstName;
    String lastName;
    @Column(columnDefinition = "DATE")
    LocalDate dateOfBirth;
    String phoneNumber;
}
