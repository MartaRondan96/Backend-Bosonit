package com.example.springdata.domain;
import com.example.springdata.controller.dto.StudentInputDTO;
import com.example.springdata.controller.dto.StudentOutputDTO;
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
//Nombre de la clase = nombre de la tabla
public class Student {
    //Nombre de los atributos = nombre de las columnas
    @Id
    @GeneratedValue
    int id;
    String name;
    String lastName;

    public Student(StudentInputDTO studentInputDTO) {
        this.id = studentInputDTO.getId();
        this.name = studentInputDTO.getName();
        this.lastName = studentInputDTO.getLastName();
    }

    public StudentOutputDTO studentToStudentOutputDTO() {
        return new StudentOutputDTO(
                this.id,
                this.name,
                this.lastName
        );
    }

}
