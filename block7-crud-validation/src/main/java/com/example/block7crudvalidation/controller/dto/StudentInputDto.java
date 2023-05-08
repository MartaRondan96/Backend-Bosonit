package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {
    Integer id;
    int id_Persona;
    int num_hours_week;
    String comments;
    String branch;
    Integer id_Profesor;
}
