package com.formacion.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String home(){
        String home ="This is the home from my First program!";
        return home;
    }

    @GetMapping("/hello/{nombre}")
    public String hello(@PathVariable String nombre){
        if(!nombre.equals("Mart"))
            return "Hello " + nombre;
        else
            return "hey Marta";

    }

    @GetMapping("/bye")
    public String bye(){
        return "Bye, you're exiting...";
    }
}
