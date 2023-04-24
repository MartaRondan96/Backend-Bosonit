package com.example.block6personcontrollers;

import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Override
    public Person createPerson(String nombre, String poblacion, int edad) {
        return new Person(nombre,poblacion,edad);
    }
}
