package com.example.Clinica.Odontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.Clinica.Odontologica.model.DentistaModel;

@Repository
public interface DentistaRepository extends JpaRepository<DentistaModel, Long> {

}
