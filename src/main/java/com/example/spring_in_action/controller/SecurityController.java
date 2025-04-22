package com.example.spring_in_action.controller;


import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SecurityController {

    @GetMapping
    public String index() {
        return "Hello World";
    }

    @GetMapping("/get-info")
    @Async
    public String getInfo() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            return "User is not authenticated";
        }
        System.out.println(authentication.toString());
        return "Authenticated as: " + authentication.getName();
    }

}
