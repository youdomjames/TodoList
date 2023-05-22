package com.todo.service;

import com.todo.dto.URIs;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public record UserService(WebClient.Builder webClientBuilder) {
    public boolean userExists(String userId) {
        return Boolean.TRUE.equals(webClientBuilder.build().get()
                .uri(URIs.USERS.concat("/exists/").concat(userId)).retrieve().bodyToMono(Boolean.class).block());
    }
}
