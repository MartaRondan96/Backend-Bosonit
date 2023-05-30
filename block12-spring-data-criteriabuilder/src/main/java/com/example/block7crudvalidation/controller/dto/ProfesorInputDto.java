package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorInputDto {
    Integer idPersona;
    String comments;
    String branch;
}
