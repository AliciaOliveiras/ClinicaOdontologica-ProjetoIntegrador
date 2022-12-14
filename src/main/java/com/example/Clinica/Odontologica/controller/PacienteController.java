package com.example.Clinica.Odontologica.controller;

import com.example.Clinica.Odontologica.dto.PacienteDto;
import com.example.Clinica.Odontologica.exception.BadRequestException;
import com.example.Clinica.Odontologica.exception.ResourceNotFoundException;
import com.example.Clinica.Odontologica.model.PacienteModel;
import com.example.Clinica.Odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping()
    public ResponseEntity buscarTodos(){
        return pacienteService.buscarTodosPacientes();
    }

    @PostMapping()
    public ResponseEntity<PacienteModel> registrarPaciente(@RequestBody PacienteModel paciente) throws ResourceNotFoundException{
        return ResponseEntity.ok(pacienteService.salvarPaciente(paciente));
    }

    @PutMapping()
    public ResponseEntity atualizarPaciente(@RequestBody PacienteDto pacienteDto){
        PacienteDto pacienteDtoAlterado = pacienteService.atualizarPaciente(pacienteDto);
        if(pacienteDtoAlterado == null){
            return new ResponseEntity<>("Erro ao alterar paciente", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Paciente alterado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> buscarPaciente(@PathVariable Long id)throws ResourceNotFoundException {

        try {
            return ResponseEntity.ok(pacienteService.buscarPaciente(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o paciente que você quis buscar por id de número: " + id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarPaciente(@PathVariable Long id)throws ResourceNotFoundException {

        try {
            pacienteService.deletarPaciente(id);
            return ResponseEntity.ok("Deletado");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o paciente para deletar de id: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadRequest(BadRequestException ex){

        //essa classe vai pegar o erro para nós e reportar para o ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
