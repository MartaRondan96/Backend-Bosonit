package com.example.springdata.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDTO {
    int id;
    String name;
    String lastName;
}
