package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
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

    public ProfesorOutputDto profesorToProfesorOutputDto(){
        return new ProfesorOutputDto(
                this.id,
                this.persona.getId(),
                this.comments,
                this.branch
        );
    }
    public Profesor(ProfesorInputDto profesorInputDto){
        Persona persona = new Persona();
        persona.setId(profesorInputDto.getId());
        this.comments = profesorInputDto.getComments();
        this.branch = profesorInputDto.getBranch();
    }
}
