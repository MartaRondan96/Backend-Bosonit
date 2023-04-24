package com.example.block6personcontrollers.controllers;

import com.example.block6personcontrollers.models.Ciudad;
import com.example.block6personcontrollers.models.Person;
import com.example.block6personcontrollers.servicesImpl.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("controlador1/")
public class Controller1 {
    @Autowired
    PersonaServiceImpl personaServiceImpl;
    @Autowired
    @Qualifier("list")
    List ciudadesList;
    @GetMapping(value="addPersona/")
    public Person addPerson(@RequestHeader("nombre") String nombre, @RequestHeader("poblacion") String poblacion,
                            @RequestHeader("edad") int edad){
        return personaServiceImpl.createPerson(nombre,poblacion,edad);
    }

    @PostMapping(value="addCiudad/")
    public ResponseEntity addCiudad(@RequestBody Ciudad ciudad){
        Ciudad c = new Ciudad(ciudad.getNombre(),ciudad.getNumHabitantes());
        ciudadesList.add(c);
        return ResponseEntity.status(200).body(HttpStatus.OK);
    }
}
