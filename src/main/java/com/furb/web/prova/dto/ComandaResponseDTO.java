package com.furb.web.prova.dto;

import com.furb.web.prova.model.Produto;
import java.util.List;

public class ComandaResponseDTO {
    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
    private List<Produto> produtos;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}