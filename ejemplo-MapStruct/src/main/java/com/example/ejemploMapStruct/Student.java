package com.example.ejemploMapStruct;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    public String name;
    public String lastName;
    public StudentDetails student_details;

}
