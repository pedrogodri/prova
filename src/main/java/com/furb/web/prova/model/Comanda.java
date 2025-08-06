package com.furb.web.prova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comandas")
public class Comanda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "ID do usuário é obrigatório")
    @Column(nullable = false)
    private Long idUsuario;
    
    @NotBlank(message = "Nome do usuário é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nomeUsuario;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\d{8,11}$", message = "Telefone deve conter entre 8 e 11 dígitos")
    @Column(nullable = false, length = 11)
    private String telefoneUsuario;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "comanda_produtos",
        joinColumns = @JoinColumn(name = "comanda_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();
    
    
    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }
    
    public void removeProduto(Produto produto) {
        this.produtos.remove(produto);
    }
    
    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", telefoneUsuario='" + telefoneUsuario + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}