package com.example.block12mongodb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDto {
    private int id_persona;
    private String name;
    private String surname;
    private boolean active;
    private Date created_date;
}
