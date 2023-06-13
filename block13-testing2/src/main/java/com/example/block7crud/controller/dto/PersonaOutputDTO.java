package com.example.block7crud.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDTO {
    int id;
    String nombre;
    int edad;
    String poblacion;
}
