package com.example.block6personcontrollers.controllers;

import com.example.block6personcontrollers.models.Person;
import com.example.block6personcontrollers.servicesImpl.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controlador1/")
public class AddPersonaController {
    @Autowired
    PersonaServiceImpl personaServiceImpl;
    @GetMapping(value="addPersona/")
    public Person addPerson(@RequestHeader("nombre") String nombre, @RequestHeader("poblacion") String poblacion,
                            @RequestHeader("edad") int edad){
        return personaServiceImpl.createPerson(nombre,poblacion,edad);
    }
}
