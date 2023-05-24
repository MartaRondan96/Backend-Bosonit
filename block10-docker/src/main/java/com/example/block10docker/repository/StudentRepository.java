package com.example.block10docker.repository;

import com.example.block10docker.domain.Persona;
import com.example.block10docker.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByPersona(Persona persona);
}
