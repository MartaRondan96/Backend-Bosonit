package com.example.block7crudvalidation.controller.dto;

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
public class Aumnos_EstudiosOutputDto {
    Integer id;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;
    Integer id_Profesor;
    List<Integer> studentIds;
}
