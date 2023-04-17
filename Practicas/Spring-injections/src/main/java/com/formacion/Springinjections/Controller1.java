package com.formacion.Springinjections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {
    @Autowired
    Component1 component1;
    Component2 component2;
    @Autowired
    Component3 component3;
    public Controller1(Component1 getComponent1, Component2 component2){
        this.component1 = getComponent1;
        this.component2 = component2;
        System.out.println("Hola desde el controlador 1");
        System.out.println("Cargando componente..."+component1.nombre);
    }
    @GetMapping("/")
    public String hola (){
        var component2 = new Component2();
        return "hola " + component2.saluda();
    }

    @GetMapping("/3")
    public String hola3 (){
        return "hola " + component3.saluda();
    }
}
