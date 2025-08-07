package com.furb.web.prova.config;

import com.furb.web.prova.repository.ProdutoRepository;
import com.furb.web.prova.repository.UserRepository;
import com.furb.web.prova.model.Produto;
import com.furb.web.prova.model.User;
import com.furb.web.prova.model.enums.UserRole;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DataLoader(UserRepository userRepository, 
                     ProdutoRepository produtoRepository,
                     @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Criar usuário padrão se não existir
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole(UserRole.ADMIN);
            userRepository.save(admin);
            System.out.println("Usuário admin criado com senha: 123456");
        }
        
        if (!userRepository.existsByUsername("joao")) {
            User joao = new User();
            joao.setUsername("joao");
            joao.setPassword(passwordEncoder.encode("123456"));
            joao.setRole(UserRole.USER);
            userRepository.save(joao);
            System.out.println("Usuário joao criado com senha: 123456");
        }
        
        // Criar produtos de exemplo se não existirem
        if (produtoRepository.count() == 0) {
            Produto xSalada = new Produto(null, "X-Salada", 30.0);
            Produto xBacon = new Produto(null, "X-Bacon", 35.0);
            Produto xGalinha = new Produto(null, "X-Galinha", 25.0);
            Produto xTudo = new Produto(null, "X-Tudo", 40.0);
            Produto refrigerante = new Produto(null, "Refrigerante", 8.0);
            Produto batataFrita = new Produto(null, "Batata Frita", 15.0);

            produtoRepository.save(xSalada);
            produtoRepository.save(xBacon);
            produtoRepository.save(xGalinha);
            produtoRepository.save(xTudo);
            produtoRepository.save(refrigerante);
            produtoRepository.save(batataFrita);

            System.out.println("Produtos de exemplo criados!");
        }
    }
}