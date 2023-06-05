package com.task.service;

import com.task.dto.URIs;
import com.task.dto.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public record UserService(WebClient.Builder webClientBuilder) {
    public boolean userExists(String userId) {
        return Boolean.TRUE.equals(webClientBuilder.build().get()
                .uri(URIs.USERS.concat("/exists/").concat(userId)).retrieve().bodyToMono(Boolean.class).block());
    }

    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(webClientBuilder.build().get()
                .uri(URIs.USERS.concat("/").concat(userId)).retrieve().bodyToMono(User.class).block());
    }
}
