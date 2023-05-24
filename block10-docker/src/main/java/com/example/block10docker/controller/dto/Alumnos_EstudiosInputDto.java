package com.example.block10docker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
