package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controlador2/")
public class GetPersonaController {
    @Autowired
    PersonaServiceImpl personaServiceImpl;
    @GetMapping(value="getPersona/")
    public Person getPerson(@RequestHeader("nombre") String nombre, @RequestHeader("poblacion") String poblacion,
                            @RequestHeader("edad") int edad){
        return personaServiceImpl.createPerson(nombre,poblacion,edad*2);
    }
}
