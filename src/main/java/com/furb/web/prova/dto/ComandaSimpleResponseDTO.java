package com.furb.web.prova.dto;

public class ComandaSimpleResponseDTO {
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
    
    // Getters and Setters
    public Long getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }
    
    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }
}