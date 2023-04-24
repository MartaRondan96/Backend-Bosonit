package com.example.block6personcontrollers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class Person {
    String nombre, poblacion;
    int edad;

}
