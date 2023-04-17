package com.formacion.Springinjections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Component2 {
    @Autowired
    Component1 component1;
    public Component2(){
        System.out.println("Iniciando el componente 2");
    }

    public String saluda(){

        if(component1  ==null)
            return "Componente 1 es nulo";
        else
            return "Hola desde componente 2" + component1.saluda();
    }
}
