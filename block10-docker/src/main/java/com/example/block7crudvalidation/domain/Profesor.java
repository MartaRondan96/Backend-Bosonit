package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Profesores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToOne
    @JoinColumn(name="id_Persona")
    private Persona persona;
    private String comments;
    @NotNull
    private String branch;
    //OneToMany -> un profesor tiene n estudiantes
    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> studentList;

    public ProfesorFullOutputDto profesorToProfesorFullOutputDto(){
        return new ProfesorFullOutputDto(
                this.id,
                this.persona.personaToPersonaOutputDTO(),
                this.comments,
                this.branch
        );
    }
    public ProfesorSimpleOutputDto profesorToProfesorSimpleOutputDto(){
        return new ProfesorSimpleOutputDto(
                this.id,
                this.comments,
                this.branch
        );
    }
    public Profesor(ProfesorInputDto profesorInputDto){
        persona = new Persona();
        persona.setId(persona.getId());
        this.comments = profesorInputDto.getComments();
        this.branch = profesorInputDto.getBranch();
    }
}
