package com.example.Clinica.Odontologica.security;

import com.example.Clinica.Odontologica.dto.TokenDto;
import com.example.Clinica.Odontologica.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid UsuarioDto usuarioDto) {
        try {
            UsernamePasswordAuthenticationToken loginUsuario = usuarioDto.converter();
            Authentication authentication =  authManager.authenticate(loginUsuario);

            String token = tokenService.gerarToken(authentication);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setTipo("Bearer");

            return new ResponseEntity(tokenDto, HttpStatus.OK);
        }catch (AuthenticationException e){
            return new ResponseEntity("Erro ao autenticar", HttpStatus.BAD_REQUEST);
        }
    }
}
