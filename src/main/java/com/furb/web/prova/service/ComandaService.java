package com.furb.web.prova.service;

import com.furb.web.prova    .repository.ComandaRepository;
import com.furb.web.prova.repository.ProdutoRepository;
import com.furb.web.prova.dto.ComandaResponseDTO;
import com.furb.web.prova.dto.ComandaSimpleResponseDTO;
import com.furb.web.prova.dto.ComandaUpdateDTO;
import com.furb.web.prova.exception.ResourceNotFoundException;
import com.furb.web.prova.model.Comanda;
import com.furb.web.prova.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComandaService {
    
    @Autowired
    private ComandaRepository comandaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<ComandaSimpleResponseDTO> findAll() {
        List<Comanda> comandas = comandaRepository.findAllComandasSimples();
        return comandas.stream()
                .map(this::convertToSimpleResponseDTO)
                .collect(Collectors.toList());
    }
    
    public ComandaResponseDTO findById(Long id) {
        Comanda comanda = comandaRepository.findByIdWithProdutos(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comanda não encontrada com ID: " + id));
        return convertToResponseDTO(comanda);
    }
    
    public ComandaResponseDTO save(Comanda comanda) {
        // Salvar ou atualizar produtos
        List<Produto> produtosSalvos = new ArrayList<>();
        for (Produto produto : comanda.getProdutos()) {
            if (produto.getId() != null) {
                // Produto existente - buscar do banco
                Produto produtoExistente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + produto.getId()));
                produtosSalvos.add(produtoExistente);
            } else {
                // Novo produto - salvar
                Produto produtoSalvo = produtoRepository.save(produto);
                produtosSalvos.add(produtoSalvo);
            }
        }
        comanda.setProdutos(produtosSalvos);
        
        Comanda comandaSalva = comandaRepository.save(comanda);
        return convertToResponseDTO(comandaSalva);
    }
    
    public ComandaResponseDTO update(Long id, ComandaUpdateDTO updateDTO) {
        Comanda comanda = comandaRepository.findByIdWithProdutos(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comanda não encontrada com ID: " + id));

        if (updateDTO.getProdutos() != null && !updateDTO.getProdutos().isEmpty()) {
            List<Produto> produtosAtualizados = new ArrayList<>();
            for (Produto produto : updateDTO.getProdutos()) {
                if (produto.getId() != null) {
                    // Busca o produto completo pelo ID e adiciona na lista
                    Produto produtoExistente = produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + produto.getId()));
                    produtosAtualizados.add(produtoExistente);
                } else {
                    // Se não tem ID, pode lançar erro ou ignorar
                    throw new ResourceNotFoundException("Produto sem ID não pode ser atualizado");
                }
            }
            comanda.setProdutos(produtosAtualizados);
        }

        Comanda comandaAtualizada = comandaRepository.save(comanda);
        return convertToResponseDTO(comandaAtualizada);
    }
    
    public void deleteById(Long id) {
        if (!comandaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comanda não encontrada com ID: " + id);
        }
        comandaRepository.deleteById(id);
    }
    
    private ComandaSimpleResponseDTO convertToSimpleResponseDTO(Comanda comanda) {
        ComandaSimpleResponseDTO dto = new ComandaSimpleResponseDTO();
        dto.setIdUsuario(comanda.getIdUsuario());
        dto.setNomeUsuario(comanda.getNomeUsuario());
        dto.setTelefoneUsuario(comanda.getTelefoneUsuario());
        return dto;
    }
    
    private ComandaResponseDTO convertToResponseDTO(Comanda comanda) {
        ComandaResponseDTO dto = new ComandaResponseDTO();
        dto.setId(comanda.getId());
        dto.setIdUsuario(comanda.getIdUsuario());
        dto.setNomeUsuario(comanda.getNomeUsuario());
        dto.setTelefoneUsuario(comanda.getTelefoneUsuario());
        dto.setProdutos(comanda.getProdutos());
        return dto;
    }
}