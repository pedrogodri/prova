package com.furb.web.prova.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
    @NotBlank(message = "Username é obrigatório")
    private String username;
    
    @NotBlank(message = "Password é obrigatório")
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}