package com.example.block6personcontrollers.servicesImpl;

import com.example.block6personcontrollers.models.Person;
import com.example.block6personcontrollers.services.PersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Override
    public Person createPerson(String nombre, String poblacion, int edad) {
        return new Person(nombre,poblacion,edad);
    }
}
