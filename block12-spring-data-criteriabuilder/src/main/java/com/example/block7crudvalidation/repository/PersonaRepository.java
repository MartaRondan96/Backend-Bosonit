package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Integer> {
    Optional<Persona> findByUsuario(String usuario);

    Iterable<PersonaOutputDto> getCustomQuery(HashMap<String, Object> data, String order, int pageNumber, int pageSize);
}
