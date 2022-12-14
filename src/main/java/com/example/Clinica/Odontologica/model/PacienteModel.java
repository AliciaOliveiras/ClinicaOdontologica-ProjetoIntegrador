package com.example.Clinica.Odontologica.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Paciente")
public class PacienteModel {
    @Id
    @SequenceGenerator(name = "paciente_sequence",sequenceName = "paciente_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "paciente_sequence")
    private Long id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private int rg;
    private Date dataAlta;

    @OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY)
    private Set<ConsultaModel> consultas = new HashSet<>();


    public PacienteModel() {
    }

    public PacienteModel(String nome, String sobrenome, String endereco, int rg, Date dataAlta) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.rg = rg;
        this.dataAlta = dataAlta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", rg=" + rg +
                ", dataAlta=" + dataAlta +
                '}';
    }
}

