package com.example.Springanotaciones;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/variable")
public class PathVariableController {
    //Si la variable en el GetMapping tiene otro nombre que el parametro que recibe la función
    //Se especifica el value en la anotacion @PathVariable
    @GetMapping(value= "/hola/{whatever}") //Aquí se llama whatever
    public String hola(@PathVariable(value="whatever") String hola) { //La variable se llama hola
        return "Hello World " + hola;
    }
    //Recibir varios valores de PathVariable
    //Esto sería mejor hacerlo con la anotación RequestParam
    @GetMapping(value= "/hola1/{whatever}/{segundoValor}")
    public String hola1(@PathVariable(value="whatever") String hola, @PathVariable String segundoValor) {
        return "Hello World " + hola + " " + segundoValor;
    }
    //Al ser varios valores los que se reciben pueden tratarse como un Map
    @GetMapping(value= "/hola2/{whatever}/{segundoValor}")
    public String hola2(@PathVariable Map<String, String> mapaValores) {
        return "Hello World " + mapaValores.get("whatever") + " " + mapaValores.get("segundoValor");
    }

    @GetMapping(value = { "/hola3/", "/hola3/{id}" })
    public String hola3(@PathVariable(required = false) String id) {
        return id == null ? "Hello World" : "Hello World " + id;
    }
    //Utiliza el optional para el parametro que no sabe si va a recibir
    //De esta forma trata la excepcion que pueda ocurrir
    @GetMapping(value= {"/hola4/","/hola4/{id}"})
    public String hola4(@PathVariable(value="id") Optional<String> valor) {
        return valor.isPresent() ? "Tenemos valor: " + valor.get() : "No tenemos valor";
    }

}
