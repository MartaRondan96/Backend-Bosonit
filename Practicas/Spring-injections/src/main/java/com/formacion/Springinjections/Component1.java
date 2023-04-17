package com.formacion.Springinjections;

import org.springframework.stereotype.Component;

@Component
public class Component1 {
    public String nombre ="SIN NOMBRE";
    public Component1(){
        System.out.println("Iniciando componente 1");
    }
    public String saluda(){
        return "Componente con nombre "+nombre;

    }
}
