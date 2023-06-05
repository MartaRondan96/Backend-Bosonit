package com.example.block12mongodb.repository;

import com.example.block12mongodb.domain.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<Persona, Integer> {
    Persona findById(int id);
}
