package com.example.spring_in_action.service;


import com.example.spring_in_action.DTO.LoginRequest;
import com.example.spring_in_action.DTO.RegisterRequest;
import com.example.spring_in_action.DTO.ResponseDTO;
import com.example.spring_in_action.DTO.UserAuthenticated;
import com.example.spring_in_action.entity.User;

import com.example.spring_in_action.repositorty.UserRepository;
import com.example.spring_in_action.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        if (userRepository.findByUsername(request.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<ResponseDTO> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        System.out.println("Coming After");


        log.info("Coming After: {}", authentication);
        log.info("User After: {}", authentication.getPrincipal());


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserAuthenticated user = new UserAuthenticated(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPassword()
        );



        return ResponseEntity.ok(new ResponseDTO(
                "XYZABC",
                user.getId(),
                user.getUsername(),
                user.getEmail()
        ));
    }
}
