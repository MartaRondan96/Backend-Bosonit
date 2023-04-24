package com.example.block6personcontrollers;

import com.example.block6personcontrollers.models.Ciudad;
import com.example.block6personcontrollers.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Block6PersonControllersApplication{
	public static void main(String[] args) {
		SpringApplication.run(Block6PersonControllersApplication.class, args);
	}

	//Bean que crea la lista de Ciudades y añade un valor
	@Bean("list")
	public List ciudadList(){
		List<Ciudad> CiudadList = new ArrayList<>();
		CiudadList.add(new Ciudad("Cádiz",170000));
		return CiudadList;
	}
	//Crear 3 objetos Persona diferentes
	@Bean("bean1")
	public Person persona1() {
		return new Person("Marta", "San Fernando", 26);
	}
	@Bean("bean2")
	public Person persona2() {
		return new Person("Mario", "Cádiz", 26);
	}
	@Bean("bean3")
	public Person persona3() {
		return new Person("Alicia", "San Fernando", 24);
	}
}
