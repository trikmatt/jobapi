package com.example.jobapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.jobapi.config.MailConfig;
import com.example.jobapi.dto.UsuarioDTO;
import com.example.jobapi.dto.UsuarioInserirDTO;
import com.example.jobapi.exception.EmailException;
import com.example.jobapi.model.Usuario;
import com.example.jobapi.model.UsuarioPerfil;
import com.example.jobapi.repository.UsuarioPerfilRepository;
import com.example.jobapi.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
	private UsuarioRepository usuarioRepository;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;

	@Autowired
	MailConfig mailConfig;

	public List<UsuarioDTO> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO= new ArrayList<>();
		for (Usuario u : usuarios) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(u);
			usuariosDTO.add(usuarioDTO);
		}
		return usuarios.stream().map(u-> new UsuarioDTO(u)).collect(Collectors.toList());
	}

	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		
		if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Este email já está cadastrado !");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));
		usuario = usuarioRepository.save(usuario);

		for (UsuarioPerfil usuarioPerfil : usuarioInserirDTO.getUsuarioPerfis()){
			usuarioPerfil.setUsuario(usuario);
			usuarioPerfil.setDataCriacao(LocalDate.now());
			usuarioPerfil.setPerfil(perfilService.buscar(usuarioPerfil.getPerfil().getId()));
		}
		usuarioPerfilRepository.saveAll(usuarioInserirDTO.getUsuarioPerfis());
		mailConfig.enviarEmail(usuario.getEmail(), "Confirmação de Cadastro de Usuário", usuario.toString());
		return new UsuarioDTO(usuario);
	}	
}