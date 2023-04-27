package com.example.ejemploMapStruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    public String studentName;
    public int studentAge;
    public StudentDetails details;
}
