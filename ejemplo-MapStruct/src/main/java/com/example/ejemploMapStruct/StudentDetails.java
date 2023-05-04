package com.example.ejemploMapStruct;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {

    private String lastName;
    private String phone_number;

}

