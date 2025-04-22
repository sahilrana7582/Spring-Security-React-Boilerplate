package com.example.spring_in_action.service;



import com.example.spring_in_action.DTO.LoginRequest;
import com.example.spring_in_action.DTO.RegisterRequest;
import com.example.spring_in_action.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> registerUser(RegisterRequest registerRequest);
    ResponseEntity<ResponseDTO> authenticateUser(LoginRequest loginRequest);
}
