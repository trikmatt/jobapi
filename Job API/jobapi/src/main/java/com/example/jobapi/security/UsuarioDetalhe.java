package com.example.jobapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.jobapi.model.Usuario;
import com.example.jobapi.model.UsuarioPerfil;

public class UsuarioDetalhe implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    private Optional<Usuario> usuario;
    
    public UsuarioDetalhe(Optional<Usuario> usuario) {
        super();
        this.usuario = usuario;
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UsuarioPerfil usuarioPerfil : usuario.get().getUsuarioPerfis()){
            grantedAuthorities.add(new SimpleGrantedAuthority(usuarioPerfil.getPerfil().getNome()));
        }
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		
		return usuario.get().getSenha();
	}

	@Override
	public String getUsername() {
	
		return usuario.get().getNome();
	}

    @Override
    public boolean isAccountNonExpired() {
      
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
