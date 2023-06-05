package com.example.block12mongodb;

import com.example.block12mongodb.application.PersonaDAL;
import com.example.block12mongodb.domain.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Block12MongodbApplication {

		private final PersonaDAL personDAL;
		@Autowired
		public Block12MongodbApplication(PersonaDAL personDAL) {
			this.personDAL = personDAL;
		}
		public static void main(String[] args) {
			SpringApplication.run(Block12MongodbApplication.class, args);
		}

}
