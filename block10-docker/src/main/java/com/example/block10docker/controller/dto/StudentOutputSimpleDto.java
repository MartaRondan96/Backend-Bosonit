package com.example.block10docker.controller.dto;

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
    Set<Alumnos_EstudiosOutputDto> estudios;
}
