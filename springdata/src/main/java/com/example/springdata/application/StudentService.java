package com.example.springdata.application;

import com.example.springdata.controller.dto.StudentInputDTO;
import com.example.springdata.controller.dto.StudentOutputDTO;
//Los servicios reciben y devuelven DTOs no objetos
public interface StudentService {
    StudentOutputDTO addStudent(StudentInputDTO student);
    StudentOutputDTO getStudentById(int id);
    void deleteStudentById( int id);
    Iterable<StudentOutputDTO> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDTO updateStudent(StudentInputDTO student);
}
