package com.example.jobapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "id.perfil")
    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.nome;
    }

    public void setName(String name) { 
        this.nome = name;
    }


    public Set<UsuarioPerfil> getUsuarioPerfis() {
        return this.usuarioPerfis;
    }

    public String getNome() {
        return nome;
    }  

}
