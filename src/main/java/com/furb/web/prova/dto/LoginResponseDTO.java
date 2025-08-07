package com.furb.web.prova.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;
    
    public LoginResponseDTO(String token, String username) {
        this.token = token;
        this.username = username;
    }
}