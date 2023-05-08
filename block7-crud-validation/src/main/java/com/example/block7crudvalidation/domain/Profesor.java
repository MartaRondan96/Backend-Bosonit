package com.example.block7crudvalidation.domain;

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
    Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_Persona")
    Persona persona;
    String comments;
    @NotNull
    String branch;
    //OneToMany -> un profesor tiene n estudiantes
    @OneToMany(fetch = FetchType.LAZY)
    List<Student> studentList;
}
