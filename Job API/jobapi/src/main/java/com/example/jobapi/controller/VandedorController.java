package com.example.jobapi.controller;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import com.example.jobapi.model.Vendedor;
import com.example.jobapi.repository.VendedorRepository;


@RestController
@RequestMapping("/vendedores")
public class VandedorController {
    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping
	public ResponseEntity<Page<Vendedor>> findBySalarioBetween(@PageableDefault(sort = "nome", direction = Direction.DESC,page = 2,size = 8) Pageable pageable) {
		Page<Vendedor> vendedores = vendedorRepository.findAll(pageable);
		return ResponseEntity.ok(vendedores);
	}
    @GetMapping("/salarios")
	public ResponseEntity<Page<Vendedor>> findBySalarioBetween(@RequestParam(defaultValue = "1000") Double valorMinimo, @RequestParam(defaultValue = "2000") Double valorMaximo,
			Pageable pageable) {
		Page<Vendedor> vendedores = vendedorRepository.findBySalarioBetween(valorMinimo, valorMaximo, pageable);
		return ResponseEntity.ok(vendedores);
	}
    @GetMapping("/nomes")
    public ResponseEntity<Page<Vendedor>>lfindByNomeContainingIgnoreCase(@RequestParam(defaultValue="")String paraNome,
    Pageable pageable){
        Page<Vendedor> vendedores = vendedorRepository.findByNomeContainingIgnoreCase(paraNome, pageable);
		return ResponseEntity.ok(vendedores);
    }
    @GetMapping("/data")
	public ResponseEntity<Page<Vendedor>> listarPorData(@RequestParam String paramDataNascimento, Pageable pageable){
		LocalDate dataNascimento = LocalDate.parse(paramDataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Page<Vendedor> vendedores = vendedorRepository.findByDataNascimentoGreaterThan(dataNascimento, pageable);
		return ResponseEntity.ok(vendedores);
	}
}
