package com.example.spring_in_action.controller;


import com.example.spring_in_action.DTO.LoginRequest;
import com.example.spring_in_action.DTO.RegisterRequest;
import com.example.spring_in_action.DTO.ResponseDTO;

import com.example.spring_in_action.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> authenticateUser( @RequestBody LoginRequest loginRequest) {

        System.out.println("COming Here");
        return authService.authenticateUser(loginRequest);
    }
}

