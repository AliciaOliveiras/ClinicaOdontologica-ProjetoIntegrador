package com.example.Clinica.Odontologica.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Consulta")
public class ConsultaModel {

    @Id
    @SequenceGenerator(name = "consulta_sequence",sequenceName = "consulta_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "consulta_sequence")
    private Long id;
    private Date dataConsulta;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "dentista_id")
    private DentistaModel dentista;

    public ConsultaModel() {
    }

    public ConsultaModel(Date dataConsulta, PacienteModel paciente, DentistaModel dentista) {
        this.dataConsulta = dataConsulta;
        this.paciente = paciente;
        this.dentista = dentista;
    }

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

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public DentistaModel getDentista() {
        return dentista;
    }

    public void setDentista(DentistaModel dentista) {
        this.dentista = dentista;
    }

    @Override
    public String toString() {
        return "ConsultaModel{" +
                "id=" + id +
                ", dataConsulta=" + dataConsulta +
                ", dentista=" + dentista +
                ", paciente=" + paciente +
                '}';
    }
}
