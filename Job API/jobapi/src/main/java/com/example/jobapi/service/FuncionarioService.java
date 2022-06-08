package com.example.jobapi.service;

import com.example.jobapi.dto.FuncionarioDTO;
import com.example.jobapi.model.Funcionario;
import com.example.jobapi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FotoService fotoService;

    public List<FuncionarioDTO> listar() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        List<FuncionarioDTO> funcionariosDTOS = new ArrayList<>();
        for(Funcionario funcionario : funcionarios) {
            funcionariosDTOS.add(inserirUri(funcionario));
        }
        return funcionariosDTOS;
     }


    public FuncionarioDTO inserirUri(Funcionario funcionario) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionario/{id}/foto")
                .buildAndExpand(funcionario.getId()).toUri();
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setUrl(uri.toString());
        return funcionarioDTO;
    }

    public FuncionarioDTO buscar(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return inserirUri(funcionario.get());
    }


    public FuncionarioDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException{
        fotoService.inserir(funcionarioRepository.save(funcionario), file);
        return inserirUri(funcionario);
    }

}
