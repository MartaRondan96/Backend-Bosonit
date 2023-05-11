package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.domain.Alumnos_Estudios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Alumnos_EstudiosRepository extends JpaRepository<Alumnos_Estudios,Integer> {
    Set<Alumnos_Estudios> findByIdStudent(int id_student);
}
