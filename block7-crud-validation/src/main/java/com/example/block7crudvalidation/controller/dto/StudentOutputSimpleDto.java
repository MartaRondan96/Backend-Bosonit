package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Alumnos_Estudios;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputSimpleDto {
    Integer id;
    int num_hours_week;
    String comments;
    String branch;
}
