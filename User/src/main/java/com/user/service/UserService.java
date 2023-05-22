package com.user.service;

import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.util.exceptions.*;
import com.user.util.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.Optional;

@Slf4j
@Service
public record UserService(UserRepository userRepository, UserMapper userMapper,
                          KeycloakAdminService keycloakAdminService) {

    public UserResponse getUserById(@NonNull String userId) {
        return userMapper.usertoUserResponse(getUserByUserId(userId));
    }

    public Page<UserResponse> getUsers(String filter, int page, int size,
                                       String sortingDirection, String... sortingFields) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(sortingDirection), sortingFields));
        return userRepository.findAll(filter, pageRequest).map(userMapper::usertoUserResponse);
    }

    public UserResponse createUser(UserRequest userRequest) {
        Optional<User> foundUser = userRepository.findByEmail(userRequest.getEmail());
        if (foundUser.isPresent()) {
            throw new DuplicateObjectException("User already present");
        }
        ClientResponse response = keycloakAdminService.addUser(userRequest.getEmail(), userRequest.getPassword());
        if (response.statusCode() != HttpStatus.CREATED) {
            throw new ObjectCreationException("User not added to keycloak");
        }
        log.info("User added to keycloak");
        User savedUser = userRepository.save(userMapper.userRequestToUser(userRequest));
        return userMapper.usertoUserResponse(savedUser);
    }

    public void updateUser(UserRequest userRequest, @NonNull String userId) {
        if (userRequest.getEmail() != null) {
            throw new ObjectUpdateException("Email cannot be updated");
        }
        User foundUser = getUserByUserId(userId);
        userMapper.updateUser(userRequest, foundUser);
        userRepository.save(foundUser);
    }

    public boolean userExists(String userId) {
        return userRepository.existsById(userId);
    }

    public void deleteUser(String email) {
        User foundUser = getUserByEmail(email);
        userRepository.delete(foundUser);
        if (userRepository.existsById(foundUser.getFirstName())) {
            throw new ObjectDeletionException("User " + foundUser.getUserId() + " not deleted");
        }
        ClientResponse response = keycloakAdminService.removeUser(foundUser.getEmail());
        if (response.statusCode() != HttpStatus.OK) {
            throw new ObjectDeletionException("User " + foundUser.getUserId() + " not removed from keycloak");
        }
        log.info("User " + foundUser.getUserId() + " deleted and removed from keycloak");
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    private User getUserByUserId(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }
}

