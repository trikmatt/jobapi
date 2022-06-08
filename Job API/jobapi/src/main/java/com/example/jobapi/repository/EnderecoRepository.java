package com.example.jobapi.repository;



import java.util.Optional;

import com.example.jobapi.model.Endereco;


import org.springframework.data.jpa.repository.JpaRepository;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
  public Optional<Endereco> findByCep(String cep);
}
