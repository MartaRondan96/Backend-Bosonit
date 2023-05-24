package com.example.block10docker.repository;

import com.example.block10docker.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Integer> {
    Optional<Persona> findByUsuario(String usuario);

}
