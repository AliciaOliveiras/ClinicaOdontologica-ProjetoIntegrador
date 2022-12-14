package com.example.Clinica.Odontologica.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class PerfilModel implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String descricao;

    public PerfilModel() {
    }

    public PerfilModel(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getAuthority() {
        return this.getDescricao();
    }
}
