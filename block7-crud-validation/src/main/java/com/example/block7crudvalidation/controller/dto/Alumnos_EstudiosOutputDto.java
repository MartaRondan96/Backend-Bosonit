package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumnos_EstudiosOutputDto {
    Integer id;
    Profesor profesor;
    List<Student> studentList;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;

}
