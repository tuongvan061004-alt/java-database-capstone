package com.project.backend.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken(String username) {
        return "JWT-TOKEN-" + username;
    }
}
