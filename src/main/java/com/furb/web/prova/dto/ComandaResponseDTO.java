package com.furb.web.prova.dto;

import com.furb.web.prova.model.Produto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComandaResponseDTO {
    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
    private List<Produto> produtos;
}