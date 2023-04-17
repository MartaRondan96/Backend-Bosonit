package com.formacion.Springinjections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBeans {
    public ConfigurationBeans(){
        System.out.println("Iniciando ConfiguracionBeans");
    }
    @Bean
    Component3 getComponent3(){
        var c = new Component3();
        return c;
    }

    @Bean
    Component1 getComponent1(){
        var c = new Component1();
        c.nombre="NOMBRE BEAN";
        return c;
    }
}
