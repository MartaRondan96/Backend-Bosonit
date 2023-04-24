package com.example.block6personcontrollers;

import com.example.block6personcontrollers.models.Ciudad;
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

	@Bean("list")
	public List ciudadList(){
		List<Ciudad> CiudadList = new ArrayList<>();
		CiudadList.add(new Ciudad("Cádiz",170000));
		return CiudadList;
	}

}
