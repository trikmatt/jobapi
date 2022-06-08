package com.example.jobapi.repository;

import java.util.Optional;

import com.example.jobapi.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
    
    public Optional<Usuario>findByNome(String nome);
}
