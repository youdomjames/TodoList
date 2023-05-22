package com.user.controller;

import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public record UserController(UserService userService) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable String id) {return userService.getUserById(id);}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> getUsers(@RequestParam(required = false) String filter,
                                       @RequestParam int page, @RequestParam int size,
                                       @RequestParam String sortingDirection, @RequestParam(name = "sortingField") String ...sortingFields) {
        return userService.getUsers(filter, page, size, sortingDirection, sortingFields);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserRequest userRequest, @PathVariable String id) {
        userService.updateUser(userRequest, id);
    }

    @GetMapping("/exists/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean userExists(@PathVariable String userId) {
        return userService.userExists(userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
