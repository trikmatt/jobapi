package com.example.jobapi.repository;

import com.example.jobapi.model.Perfil;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    
}
