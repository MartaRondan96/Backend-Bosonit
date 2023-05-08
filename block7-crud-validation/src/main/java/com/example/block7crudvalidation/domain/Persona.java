package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
     int id;
    //NOT NULL max-length: 10 min-length: 6
     String usuario;
    //NOT NULL
     String password;
    //NOT NULL
     String name;
     String surname;
    //NOT NULL
     String company_email;
    //NOT NULL
     String personal_email;
    //NOT NULL
     String city;
    //NOT NULL
     Boolean active;
    //NOT NULL
     Date create_date;
     String image_url;
     Date termination_date;
     //Student-Persona relacion OneToOne
     @OneToOne
     Student student;
    //Profesor-Persona relacion OneToOne
     @OneToOne
     Profesor profesor;
    public Persona(PersonaInputDto personaInputDTO){
        this.id = personaInputDTO.getId();
        this.usuario = personaInputDTO.getUsuario();
        this.password = personaInputDTO.getPassword();
        this.name = personaInputDTO.getName();
        this.surname = personaInputDTO.getSurname();
        this.company_email = personaInputDTO.getCompany_email();
        this.personal_email = personaInputDTO.getPersonal_email();
        this.city = personaInputDTO.getCity();
        this.active = personaInputDTO.getActive();
        this.create_date = personaInputDTO.getCreate_date();
        this.image_url = personaInputDTO.getImage_url();
        this.termination_date = personaInputDTO.getTermination_date();
    }
    public PersonaOutputDto personaToPersonaOutputDTO(){
        return new PersonaOutputDto(
                this.id,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.active,
                this.create_date,
                this.image_url,
                this.termination_date
        );
    }
}
