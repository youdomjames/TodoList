package com.user.repository;

import com.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.phoneNumber = ?1 OR u.firstName = ?1 OR u.lastName = ?1 OR u.dateOfBirth = ?1")
    Page<User> findAll(String filter, PageRequest pageRequest);
}
