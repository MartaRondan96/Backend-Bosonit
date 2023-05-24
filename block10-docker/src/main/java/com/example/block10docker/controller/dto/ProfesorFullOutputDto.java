package com.example.block10docker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorFullOutputDto {
    Integer id;
    PersonaOutputDto persona;
    String comments;
    String branch;
}
