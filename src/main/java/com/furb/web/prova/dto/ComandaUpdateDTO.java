package com.furb.web.prova.dto;

import com.furb.web.prova.model.Produto;
import java.util.List;

public class ComandaUpdateDTO {
    private List<Produto> produtos;
    
    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}