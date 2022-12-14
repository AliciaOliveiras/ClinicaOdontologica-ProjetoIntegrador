package com.example.Clinica.Odontologica.exception.handler;


import com.example.Clinica.Odontologica.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // vai fazer o tratamento de todos os erros
public class GlobalExceptions {
    //para funcionar aqui no spring boot temos que implementar um método que vai pegar
    //o erro para nós e vai tornar ele como ResponseEntity

    @ExceptionHandler // vai utilizar para fazer o tratamento do erro
    public ResponseEntity<String> processarErrorNotFound(ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + " Global");
    }
}