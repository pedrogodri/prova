package com.furb.web.prova.model;

import com.furb.web.prova.model.enums.UserRole;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O Nome de usuário é obrigatório")
    @Size(min = 3, max = 20, message = "O Nome de usuário deve ter entre 3 e 20 caracteres")
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotBlank(message = "A Senha é obrigatória")
    @Size(min = 6, message = "A Senha deve ter pelo menos 6 caracteres")
    @Column(nullable = false)
    private String password;
    
    @NotNull(message = "O Papel do usuário é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
}