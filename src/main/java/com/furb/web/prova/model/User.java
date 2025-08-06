package com.furb.web.prova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    
    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 20, message = "Username deve ter entre 3 e 20 caracteres")
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotBlank(message = "Password é obrigatório")
    @Size(min = 6, message = "Password deve ter pelo menos 6 caracteres")
    @Column(nullable = false)
    private String password;
    
    @NotBlank(message = "Role é obrigatório")
    @Column(nullable = false)
    private String role;
}