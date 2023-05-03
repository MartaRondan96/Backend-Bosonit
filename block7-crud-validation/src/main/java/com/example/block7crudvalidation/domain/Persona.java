package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private int id;
    //NOT NULL max-length: 10 min-length: 6
    private String usuario;
    //NOT NULL
    private String password;
    //NOT NULL
    private String name;
    private String surname;
    //NOT NULL
    private String company_email;
    //NOT NULL
    private String personal_email;
    //NOT NULL
    private String city;
    //NOT NULL
    private Boolean active;
    //NOT NULL
    private Date create_date;
    private String image_url;
    private Date termination_date;
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
