package com.example.jobapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jobapi.model.Usuario;
import com.example.jobapi.repository.UsuarioRepository;
import com.example.jobapi.security.UsuarioDetalhe;
@Service
public class UsuarioDetalheimpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDetalheimpl(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByNome(username);
        if(usuario.isPresent()){
            return new UsuarioDetalhe(usuario);
        }
        throw new UsernameNotFoundException("Usuário inváçido!");
    }
    
}
