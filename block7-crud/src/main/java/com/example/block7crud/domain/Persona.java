package com.example.block7crud.domain;

import com.example.block7crud.controller.dto.PersonaInputDTO;
import com.example.block7crud.controller.dto.PersonaOutputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    int id;
    String nombre;
    int edad;
    String poblacion;

    //Método para convertir un objeto personaInput en persona
    //Recibe una PersonaInputDTO y devuelve una Persona
    public Persona(PersonaInputDTO personaInputDTO){
        this.id = personaInputDTO.getId();
        this.nombre = personaInputDTO.getNombre();
        this.edad = personaInputDTO.getEdad();
        this.poblacion= personaInputDTO.getPoblacion();
    }
    public PersonaOutputDTO personaToPersonaOutputDTO(){
        return new PersonaOutputDTO(
                this.id,
                this.nombre,
                this.edad,
                this.poblacion);
    }
}
