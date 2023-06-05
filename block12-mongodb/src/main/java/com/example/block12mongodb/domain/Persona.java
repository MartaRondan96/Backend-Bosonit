package com.example.block12mongodb.domain;
import com.example.block12mongodb.controller.dto.PersonaInputDto;
import com.example.block12mongodb.controller.dto.PersonaOutputDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Persona")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    private int id_persona;
    private String name;
    private String surname;
    private boolean active;
    private Date created_date;

    public Persona(PersonaInputDto persona) {
        this.id_persona = persona.getId_persona();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.active = persona.isActive();
        this.created_date = persona.getCreated_date();
    }

    public PersonaOutputDto personaToPersonaOutputDto() {
        return new PersonaOutputDto(
                this.id_persona,
                this.name,
                this.surname,
                this.active,
                this.created_date);
    }

}
