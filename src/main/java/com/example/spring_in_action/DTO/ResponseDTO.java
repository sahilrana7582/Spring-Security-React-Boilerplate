package com.example.spring_in_action.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private String token;
    private String id;
    private String username;
    private String email;
    private String type = "Bearer";

    public ResponseDTO(String token, String id, String username, String email) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
