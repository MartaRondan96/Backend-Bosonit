package com.example.block6personcontrollers.services;

import com.example.block6personcontrollers.models.Person;

public interface PersonaService {
    public abstract Person createPerson(String nombre, String poblacion, int edad);
}
