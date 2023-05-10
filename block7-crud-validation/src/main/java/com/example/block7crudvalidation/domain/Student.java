package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_Persona")
    private Persona persona;
    @NotNull
    @Column(name = "horas_por_semana")
    private int num_hours_week;
    @Column(name = "comentarios")
    private String comments;
    //ManyToOne -> n estudiantes tienen un profesor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Profesor")
    private Profesor profesor;
    @NotNull
    @Column(name = "rama")
    private String branch;
    @ManyToMany(mappedBy = "student")
    private Set<Alumnos_Estudios> estudios;

    public StudentOutputFullDto studentToStudentOutputFullDto() {
        return new StudentOutputFullDto(
                this.id,
                this.persona.personaToPersonaOutputDTO(),
                this.num_hours_week,
                this.comments,
                this.branch
        );
    }

    public StudentOutputSimpleDto studentToStudentOutputSimpleDto() {
        return new StudentOutputSimpleDto(
                this.id,
                this.num_hours_week,
                this.comments,
                this.branch
        );
    }
    public Student(StudentInputDto studentInputDto){
        persona = new Persona();
        this.persona.setId(studentInputDto.getIdPersona());
        this.num_hours_week = studentInputDto.getNum_hours_week();
        this.comments = studentInputDto.getComments();
        this.branch = studentInputDto.getBranch();
    }
}
