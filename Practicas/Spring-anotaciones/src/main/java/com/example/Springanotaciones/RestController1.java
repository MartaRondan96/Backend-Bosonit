package com.example.Springanotaciones;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restController")
public class RestController1 {
    //peticiones GET
    //También puede especificarse la ruta sin value=
    //Puede recibir parametros en la ruta
    @GetMapping(value= "/hola")
    public String hola(){
        return "hola";
    }
    @GetMapping(value= "/adios")
    public String adios(){
        return "adios";
    }
    @GetMapping(value= "/queTal")
    public String quetal(){
        return "que tal?";
    }
}
