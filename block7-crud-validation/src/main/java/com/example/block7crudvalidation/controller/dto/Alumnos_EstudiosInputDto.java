package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumnos_EstudiosInputDto {
    Integer idProfesor;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;
}
