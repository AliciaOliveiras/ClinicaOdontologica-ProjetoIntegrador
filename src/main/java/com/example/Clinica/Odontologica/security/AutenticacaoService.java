package com.example.Clinica.Odontologica.security;

import com.example.Clinica.Odontologica.model.UsuarioModel;
import com.example.Clinica.Odontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = usuarioRepository.findByUsername(username);
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        return usuario.get();
    }
}

