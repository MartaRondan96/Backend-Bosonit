package com.example.MapstructRelaciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    public String studentName;
    public int studentAge;
    public StudentDetails details;

}
