package com.example.Clinica.Odontologica;

import com.example.Clinica.Odontologica.model.PerfilModel;
import com.example.Clinica.Odontologica.model.UsuarioModel;
import com.example.Clinica.Odontologica.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CreateUser implements ApplicationRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder brCrypt = new BCryptPasswordEncoder();

        UsuarioModel usuario = new UsuarioModel();
        usuario.setPassword(brCrypt.encode("arabela1"));
        usuario.setUsername("Arabela");
        usuario.setPerfil(new PerfilModel());

        //suarioRepository.save(usuario);

    }
}
