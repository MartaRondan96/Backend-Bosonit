package com.example.ejemploMapStruct;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String address;
    private String phone_number;
    @OneToOne
    @JoinColumn(name = "Student_Id", nullable = false, unique = true)
    private Student student;


    public StudentDetails(StudentDetailsInputDto student_DetailsInputDto) {
        this.address = student_DetailsInputDto.getAddress();
        this.phone_number = student_DetailsInputDto.getPhone_number();
    }

    public StudentDetailsOutputDto student_DetailsToStudent_DetailsOutputDto() {
        return new StudentDetailsOutputDto(
                this.id,
                this.address,
                this.phone_number,
                this.student.getId(),
                this.student.getName(),
                this.student.getLastName()
        );
    }

