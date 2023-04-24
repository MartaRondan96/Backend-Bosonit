package com.example.block6simplecontrollers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person/")
public class PersonController {
    //Debe devolver un String  poniendo “Hola” + el contenido de la variable nombre
    @GetMapping(value="user/{nombre}")
    public String hola(@PathVariable(value= "nombre")String nombre){
        return "Hola "+ nombre;
    }
    //Recibe un objeto de la clase Persona en formato JSON, con los campos: nombre, población y edad,
    //Devolver un objeto tipo persona cuya edad sea la recibida más una.
    @PostMapping(value="useradd/")
    public Person add(@RequestBody Person person){
        Person p= new Person(person.getNombre(), person.getPoblacion(), person.getEdad()+1);
        System.out.println(p);
        return p;
    }

}
