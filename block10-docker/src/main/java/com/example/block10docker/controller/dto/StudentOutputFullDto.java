package com.example.block10docker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputFullDto {
    Integer id;
    PersonaOutputDto persona;
    int num_hours_week;
    String comments;
    String branch;

}
