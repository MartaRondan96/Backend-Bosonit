package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosInputDto;
import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosOutputDto;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Estudios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumnos_Estudios {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Profesor")
    private Profesor profesor;
    //ManyToMany -> un estudio tiene n estudiantes
    @ManyToMany
    @JoinColumn(name="Student")
    private Set<Student> student;
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "comentarios")
    private String comment;
    @NotNull
    @Column(name = "initial_date")
    private Date initial_date;
    @Column(name = "finish_date")
    private Date finish_date;


    public Alumnos_EstudiosOutputDto estudioToEstudioOutputDto(){
        return new Alumnos_EstudiosOutputDto(
          this.id,
          this.profesor.getId(),
          this.asignatura,
          this.comment,
          this.initial_date,
          this.finish_date
        );
    }

    public Alumnos_Estudios(Alumnos_EstudiosInputDto alumnos_EstudiosInputDto){
        profesor = new Profesor();
        profesor.setId(alumnos_EstudiosInputDto.getIdProfesor());
        this.asignatura = alumnos_EstudiosInputDto.getAsignatura();
        this.comment = alumnos_EstudiosInputDto.getComment();
        this.initial_date = alumnos_EstudiosInputDto.getInitialDate();
        this.finish_date = alumnos_EstudiosInputDto.getFinishDate();
    }
}
