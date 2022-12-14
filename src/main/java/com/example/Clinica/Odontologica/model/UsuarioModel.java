package com.example.Clinica.Odontologica.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 90)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private PerfilModel perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.perfil);
    }


    public UsuarioModel() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  public PerfilModel getPerfil() {
    return perfil;
  }

  public void setPerfil(PerfilModel perfil) {
    this.perfil = perfil;
  }

  @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return getUsername();
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
