package com.example.block7crudvalidation.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Persona")
    Persona persona;
    @NotNull
    @Column(name = "horas_por_semana")
    int num_hours_week;
    @Column(name = "comentarios")
    String comments;
    //ManyToOne -> n estudiantes tienen un profesor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Profesor")
    Profesor profesor;
    @NotNull
    @Column(name = "rama")
    String branch;
    @ManyToMany(mappedBy = "student")
    Set<Alumnos_Estudios> estudios;
}
