package com.example.ejemploMapStruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoFull {
    public String name;
    public int age;
    public String lastName;
    public String phoneNumber;

}
