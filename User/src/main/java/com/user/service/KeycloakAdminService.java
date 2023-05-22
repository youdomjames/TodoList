package com.user.service;

import com.user.dto.URIs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Slf4j
@Service
public record KeycloakAdminService(WebClient.Builder webClientBuilder) {

    public ClientResponse addUser(String email, String password) {
        return webClientBuilder.build().post()
                .uri(URIs.KEYCLOAK_ADMIN.concat("/users"), uriBuilder -> uriBuilder.queryParam("email", email).queryParam("password", password).build())
                .exchangeToMono(Mono::just)
                .block();
    }

    public ClientResponse removeUser(String email) {
        return webClientBuilder.build().delete()
                .uri(URIs.KEYCLOAK_ADMIN.concat("/users"), uriBuilder -> uriBuilder.queryParam("email", email).build())
                .exchangeToMono(Mono::just)
                .block();
    }
}
