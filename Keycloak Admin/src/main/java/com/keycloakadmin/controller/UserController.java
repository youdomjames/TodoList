package com.keycloakadmin.controller;

import com.keycloakadmin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public record UserController(UserService userService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestParam String email, @RequestParam String password){
         userService.createUser(email, password);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestParam String email){
        userService.deleteUser(email);
    }
}
