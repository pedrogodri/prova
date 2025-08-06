package com.furb.web.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.furb.web.prova.model.Comanda;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {
    
    @Query("SELECT c FROM Comanda c")
    List<Comanda> findAllComandasSimples();
    
    @Query("SELECT c FROM Comanda c LEFT JOIN FETCH c.produtos WHERE c.id = :id")
    Optional<Comanda> findByIdWithProdutos(Long id);
    
    @Query("SELECT c FROM Comanda c WHERE c.idUsuario = :idUsuario")
    List<Comanda> findByIdUsuario(Long idUsuario);
    
    @Query("SELECT c FROM Comanda c WHERE c.nomeUsuario LIKE %:nome%")
    List<Comanda> findByNomeUsuarioContaining(String nome);
}