package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Alumnos_Estudios;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputFullDto {
    Integer id;
    int num_hours_week;
    String comments;
    String branch;
    Integer idPersona;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date create_date;
    String imagen_url;
    Date termination_date;
}
