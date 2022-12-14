package com.example.Clinica.Odontologica.controller;

import com.example.Clinica.Odontologica.exception.BadRequestException;
import com.example.Clinica.Odontologica.exception.ResourceNotFoundException;
import com.example.Clinica.Odontologica.model.ConsultaModel;
import com.example.Clinica.Odontologica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping()
    public ResponseEntity buscarTodas(){

        return consultaService.buscarTodasConsultas();
    }

    @PostMapping()
    public ResponseEntity<ResponseEntity> salvarConsulta(@RequestBody ConsultaModel consulta)throws BadRequestException {
        return ResponseEntity.ok(consultaService.salvarConsulta(consulta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaModel> buscarConsulta (@PathVariable Long id)throws ResourceNotFoundException {

        try {
            return ResponseEntity.ok(consultaService.buscarConsulta(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado a consulta que você quis buscar por id de número: " + id);
        }
    }

}
