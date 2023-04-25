package com.example.springdata.repository;

import com.example.springdata.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//El primer campo es la entidad a la que trata y el segundo la clase a la que pertenece la clave primaria
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
