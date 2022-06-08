package com.example.jobapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.jobapi.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

    @Query("SELECT v FROM Vendedor v WHERE v.salario>=:valorMinimo AND v.salario<=:valorMaximo")
	Page<Vendedor>buscarSalarioPorFaixa(Double valorMinimo, Double valorMaximo, Pageable pageable);
    
    Page<Vendedor>findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);

    @Query("SELECT v FROM Vendedor v WHERE UPPER(v.nome) LIKE UPPER(CONCAT('%',:paraNome, '%'))")
    Page<Vendedor>buscarNome(String paraNome, Pageable pageable);

    Page<Vendedor>findByNomeContainingIgnoreCase(String paraNome, Pageable pageable);

    Page<Vendedor>findByDataNascimentoGreaterThan(LocalDate dataNascimento, Pageable pageable);
}
