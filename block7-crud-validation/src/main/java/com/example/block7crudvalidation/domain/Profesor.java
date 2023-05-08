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
    @OneToOne(cascade = CascadeType.ALL)
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
                this.persona,
                this.comments,
                this.branch,
                this.studentList
        );
    }
    public Profesor(ProfesorInputDto profesorInputDto){
        this.id = profesorInputDto.getId();
        this.persona = profesorInputDto.getPersona();
        this.comments = profesorInputDto.getComments();
        this.branch = profesorInputDto.getBranch();
        this.studentList = profesorInputDto.getStudentList();
    }
}
