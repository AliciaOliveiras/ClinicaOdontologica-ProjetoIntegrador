package com.example.Clinica.Odontologica.service;

import com.example.Clinica.Odontologica.dto.PacienteDto;
import com.example.Clinica.Odontologica.model.PacienteModel;
import com.example.Clinica.Odontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public PacienteModel salvarPaciente(PacienteModel paciente){
        paciente.setDataAlta(new Date());
        PacienteModel pacienteSalvo = pacienteRepository.save(paciente);
        return pacienteSalvo;
    }

    public PacienteModel buscarPaciente(Long id) {
        return pacienteRepository.findById(id).get();
    }

    public ResponseEntity buscarTodosPacientes() {
        List<PacienteModel> listPacientes = pacienteRepository.findAll();
        if (listPacientes.isEmpty()) {
            return new ResponseEntity("Nenhum paciente cadastrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listPacientes, HttpStatus.OK);
    }

    public ResponseEntity deletarPaciente (Long id){
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            return new ResponseEntity("Id do paciente não existe", HttpStatus.BAD_REQUEST);
        }
//        repository.findById(id).orElseThrow(() -> new RuntimeException());
        pacienteRepository.deleteById(id);
        return new ResponseEntity("Excluido com sucesso", HttpStatus.OK);
    }

    public PacienteDto atualizarPaciente(PacienteDto pacienteDto){
        ObjectMapper mapper = new ObjectMapper();
        Optional<PacienteModel> pacienteOptional = pacienteRepository.findById(pacienteDto.getId());
        PacienteDto pacienteAlterado = null;
        if(pacienteOptional.isEmpty()){
            return pacienteAlterado;
        }
        PacienteModel paciente = pacienteOptional.get();
        if(pacienteDto.getNome() != null){
            paciente.setNome(pacienteDto.getNome());
        }

        if(pacienteDto.getSobrenome() != null){
            paciente.setSobrenome(pacienteDto.getSobrenome());
        }
        pacienteAlterado = mapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class);
        return pacienteAlterado;
    }
}