package com.example.Clinica.Odontologica.service;

import com.example.Clinica.Odontologica.model.ConsultaModel;
import com.example.Clinica.Odontologica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    public ResponseEntity salvarConsulta(ConsultaModel consulta){
        try{
            ConsultaModel consultaSalva = consultaRepository.save(consulta);
            return new ResponseEntity(consultaSalva, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("Não foi possível salvar a consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public ConsultaModel buscarConsulta(Long id){
        return consultaRepository.findById(id).get();
    }

    public ResponseEntity buscarTodasConsultas(){
        List<ConsultaModel> listConsultas = consultaRepository.findAll();
        if(listConsultas.isEmpty()){
            return new ResponseEntity<>("Nenhuma consulta cadastrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listConsultas, HttpStatus.OK);
    }
}
