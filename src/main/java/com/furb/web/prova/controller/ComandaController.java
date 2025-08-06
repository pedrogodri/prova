package com.furb.web.prova.controller;

import com.furb.web.prova.dto.ComandaResponseDTO;
import com.furb.web.prova.dto.ComandaSimpleResponseDTO;
import com.furb.web.prova.dto.ComandaUpdateDTO;
import com.furb.web.prova.model.Comanda;
import com.furb.web.prova.service.ComandaService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comandas")
@Tag(name = "Comandas", description = "API para gerenciamento de comandas")
@SecurityRequirement(name = "bearerAuth")
public class ComandaController {
    
    @Autowired
    private ComandaService comandaService;
    
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Listar todas as comandas", description = "Retorna uma lista simplificada de todas as comandas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de comandas retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<List<ComandaSimpleResponseDTO>> getAllComandas() {
        List<ComandaSimpleResponseDTO> comandas = comandaService.findAll();
        return ResponseEntity.ok(comandas);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Buscar comanda por ID", description = "Retorna uma comanda específica com seus produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comanda encontrada"),
        @ApiResponse(responseCode = "404", description = "Comanda não encontrada"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<ComandaResponseDTO> getComandaById(@PathVariable Long id) {
        ComandaResponseDTO comanda = comandaService.findById(id);
        return ResponseEntity.ok(comanda);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Criar nova comanda", description = "Cria uma nova comanda com produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Comanda criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<ComandaResponseDTO> createComanda(@Valid @RequestBody Comanda comanda) {
        ComandaResponseDTO novaComanda = comandaService.save(comanda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaComanda);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar comanda", description = "Atualiza os produtos de uma comanda existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comanda atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Comanda não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<ComandaResponseDTO> updateComanda(@PathVariable Long id, 
                                                           @Valid @RequestBody ComandaUpdateDTO updateDTO) {
        ComandaResponseDTO comandaAtualizada = comandaService.update(id, updateDTO);
        return ResponseEntity.ok(comandaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remover comanda", description = "Remove uma comanda existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comanda removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Comanda não encontrada"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public ResponseEntity<Map<String, Object>> deleteComanda(@PathVariable Long id) {
        comandaService.deleteById(id);
        
        Map<String, Object> response = new HashMap<>();
        Map<String, String> success = new HashMap<>();
        success.put("text", "comanda removida");
        response.put("success", success);
        
        return ResponseEntity.ok(response);
    }
}