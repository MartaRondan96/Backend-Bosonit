package com.example.Springanotaciones;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/request")
public class RequestParamController {
    @GetMapping(value="/var")
    public String hola(@RequestParam String valor) {
        return valor;
    }
    @GetMapping(value="/var1")
    public String hola1(@RequestParam(required = false) String valor) {
        return "El valor es: " + valor;
    }
    @GetMapping(value="/var2")
    public String hola2(@RequestParam Optional<String> valor) {
        return valor.isPresent() ? "El valor es: " + valor.get() : "No se ha digitado valor";
    }
    @GetMapping(value="/var3")
    public String hola3(@RequestParam(defaultValue = "Valor por defecto") String valor) {
        return valor;
    }
    //No especifica los parametros, almacena todos los valores que reciba en una misma variable Map
    @GetMapping(value="/var4")
    public String hola4(@RequestParam Map<String,String> valor) {
        return valor.toString();
    }

}
