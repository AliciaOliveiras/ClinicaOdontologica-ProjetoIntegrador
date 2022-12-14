package com.example.Clinica.Odontologica.dto;

import com.example.Clinica.Odontologica.model.DentistaModel;
import com.example.Clinica.Odontologica.model.PacienteModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaDto {

    private Long id;
    private Date dataConsulta;
    private DentistaModel dentista;
    private PacienteModel paciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public DentistaModel getDentista() {
        return dentista;
    }

    public void setDentista(DentistaModel dentista) {
        this.dentista = dentista;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }
}
