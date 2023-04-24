package com.example.block6personcontrollers.controllers;

import com.example.block6personcontrollers.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controlador/")
public class BeanController {
    //Inyecta los beans creados en la clase principal
    //Cada uno devuelve una persona
    @Autowired
    @Qualifier("bean1")
    Person p1;
    @Autowired
    @Qualifier("bean2")
    Person p2;
    @Autowired
    @Qualifier("bean3")
    Person p3;
    //Método del controlador
    //Comprueba el nombre del bean que recibe y devuelve el objeto persona con el que esta relacionado
    @GetMapping(value = "bean/{bean}")
    public Person nombreBean (@PathVariable(name="bean") String name ){
       switch(name){
           case "bean1": return p1;
           case "bean2": return p2;
           case "bean3": return p3;
           default: return null;
       }
    }
}
