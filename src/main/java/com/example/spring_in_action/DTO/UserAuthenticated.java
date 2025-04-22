package com.example.spring_in_action.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticated {

    private String id;
    private String username;
    private String email;
    private String password;
}
