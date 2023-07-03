package com.task.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class ConnectedUser {

    private static String token;

    public static String getToken(){
        setToken();
        return token;
    }

    private static void setToken() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        token = jwt.getTokenValue();
    }
}
