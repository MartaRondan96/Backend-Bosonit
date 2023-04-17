package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block5CommandLineRunnerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Block5CommandLineRunnerApplication.class, args);

	}
	@PostConstruct
	public void claseInicial(){
		System.out.println("Hola desde clase inicial");
	}

	public void claseSecundaria(){
		System.out.println("Hola desde clase secundaria");
	}

	public void claseTercera(){
		System.out.println("Hola desde tercera clase");
	}

	@Override
	public void run(String... args) throws Exception {
		claseSecundaria();
		claseTercera();
	}

	//Primero se muestra el mensaje de la funcion claseInicial() y luego el resultado de las otras dos funciones
	//PostConstruct se ejecuta despues de inicializar la app y CommandLineRunner despues de desplegarla por completo
}
