package com.example.jobapi.controller;

import com.example.jobapi.dto.EnderecoDTO;
import com.example.jobapi.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @GetMapping("{cep}")
    public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep){
        EnderecoDTO enderecoDTO = enderecoService.buscar(cep);
            if(enderecoDTO == null){
                return ResponseEntity.notFound().build();                
            }
            return ResponseEntity.ok(enderecoDTO);
    }
}
