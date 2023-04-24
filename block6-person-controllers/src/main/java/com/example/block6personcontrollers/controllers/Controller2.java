package com.example.block6personcontrollers.controllers;

import com.example.block6personcontrollers.models.Ciudad;
import com.example.block6personcontrollers.models.Person;
import com.example.block6personcontrollers.servicesImpl.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("controlador2/")
public class Controller2 {
    @Autowired
    PersonaServiceImpl personaServiceImpl;
    @Autowired
    @Qualifier("list")
    List ciudadesList;

    @GetMapping(value="getPersona/")
    public Person getPerson(@RequestHeader("nombre") String nombre, @RequestHeader("poblacion") String poblacion,
                            @RequestHeader("edad") int edad){
        return personaServiceImpl.createPerson(nombre,poblacion,edad*2);
    }
    //Obtiene la lista de ciudades
    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudades (){
        return ciudadesList;
    }
}
