package com.example.Clinica.Odontologica.service;

import com.example.Clinica.Odontologica.dto.DentistaDto;
import com.example.Clinica.Odontologica.model.DentistaModel;
import com.example.Clinica.Odontologica.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    DentistaRepository dentistaRepository;

    public ResponseEntity salvarDentista(DentistaModel dentista) {
        try {
            DentistaModel dentistaSalvo = dentistaRepository.save(dentista);
            return new ResponseEntity(dentistaSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel salvar o dentista", HttpStatus.BAD_REQUEST);
        }
    }

    public DentistaModel buscarDentista(Long id) {
        return dentistaRepository.findById(id).get();
    }

    public ResponseEntity buscarTodosDentistas() {
        List<DentistaModel> listDentistas = dentistaRepository.findAll();
        if (listDentistas.isEmpty()) {
            return new ResponseEntity("Nenhum Dentista Cadastrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listDentistas, HttpStatus.OK);
    }

    public ResponseEntity deletarDentista(Long id) {
        Optional<DentistaModel> produto = dentistaRepository.findById(id);

        if (produto.isEmpty()) {
            return new ResponseEntity("Id do dentista não existe", HttpStatus.BAD_REQUEST);
        }
//        repository.findById(id).orElseThrow(() -> new RuntimeException());
        dentistaRepository.deleteById(id);
        return new ResponseEntity("Excluido com sucesso", HttpStatus.OK);
    }

    public DentistaDto atualizarDentista(DentistaDto dentistaDto){
        ObjectMapper mapper = new ObjectMapper();
        Optional<DentistaModel> dentistaOptional = dentistaRepository.findById(dentistaDto.getId());
        DentistaDto dentistaAlterado = null;
        if(dentistaOptional.isEmpty()){
            return dentistaAlterado;
        }
        DentistaModel dentista = dentistaOptional.get();
        if(dentistaDto.getNome() != null){
            dentista.setNome(dentistaDto.getNome());
        }

        if(dentistaDto.getSobrenome() != null){
            dentista.setSobrenome(dentistaDto.getSobrenome());
        }
        dentistaAlterado = mapper.convertValue(dentistaRepository.save(dentista), DentistaDto.class);
        return dentistaAlterado;
    }
}
