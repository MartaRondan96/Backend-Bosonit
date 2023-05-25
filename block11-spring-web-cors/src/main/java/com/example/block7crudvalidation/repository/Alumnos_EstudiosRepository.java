package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.domain.Alumnos_Estudios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface Alumnos_EstudiosRepository extends JpaRepository<Alumnos_Estudios,Integer> {
    @Query("SELECT ae FROM Alumnos_Estudios ae JOIN ae.student s WHERE s.id = :idStudent")
    Set<Alumnos_Estudios> findByIdStudent(Integer idStudent);
}
