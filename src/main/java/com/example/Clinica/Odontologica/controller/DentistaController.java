package com.example.Clinica.Odontologica.controller;

import com.example.Clinica.Odontologica.dto.DentistaDto;
import com.example.Clinica.Odontologica.exception.BadRequestException;
import com.example.Clinica.Odontologica.exception.ResourceNotFoundException;
import com.example.Clinica.Odontologica.model.DentistaModel;
import com.example.Clinica.Odontologica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dentistas")
public class DentistaController {


    @Autowired
    DentistaService dentistaService;

    @GetMapping()
    public ResponseEntity buscarTodosDentistas(){

        return dentistaService.buscarTodosDentistas();
    }

    @PostMapping()
    public ResponseEntity<ResponseEntity> salvarDentista(@RequestBody DentistaModel dentista)throws BadRequestException {
        return ResponseEntity.ok(dentistaService.salvarDentista(dentista));
    }

    @PutMapping()
    public ResponseEntity atualizarDentista(@RequestBody DentistaDto dentistaDto){
        DentistaDto dentistaDtoAlterado = dentistaService.atualizarDentista(dentistaDto);
        if(dentistaDtoAlterado == null){
            return new ResponseEntity<>("Erro ao alterar dentista", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Dentista alterado com sucesso", HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DentistaModel> buscarDentista(@PathVariable Long id) throws ResourceNotFoundException {

        try{
            return ResponseEntity.ok(dentistaService.buscarDentista(id));
        }catch(Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o dentista que você quis buscar por id de número: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDentista(@PathVariable Long id) throws ResourceNotFoundException {

        try {
            dentistaService.deletarDentista(id);
            return ResponseEntity.ok("Deletado");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o dentista para deletar de id: " + id);
        }
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadRequest(BadRequestException ex){

        //essa classe vai pegar o erro para nós e reportar para o ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}

