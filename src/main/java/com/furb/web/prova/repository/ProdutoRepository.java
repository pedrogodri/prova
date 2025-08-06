package com.furb.web.prova.repository;

import com.furb.web.prova.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    List<Produto> findByPrecoBetween(Double precoMin, Double precoMax);
    
    @Query("SELECT p FROM Produto p WHERE p.preco > :preco")
    List<Produto> findByPrecoGreaterThan(Double preco);
    
    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> findAllOrderByPrecoAsc();
    
    @Query("SELECT p FROM Produto p ORDER BY p.nome ASC")
    List<Produto> findAllOrderByNomeAsc();
}