package com.furb.web.prova.controller;

import com.furb.web.prova.model.Produto;
import com.furb.web.prova.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Salvar produto", description = "Salva um novo produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Produto> saveProduto(@Valid @RequestBody Produto produto) {
        Produto saved = produtoService.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Lista todos os produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    })
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Busca um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover produto", description = "Remove um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}