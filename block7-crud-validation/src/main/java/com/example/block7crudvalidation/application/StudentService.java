package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;

public interface StudentService {
    StudentOutputDto getStudentById(int id);
    StudentOutputDto addStudent(StudentInputDto persona);
    StudentOutputDto updateStudent(StudentInputDto persona, int id);
    void deleteStudentById(int id);
    Iterable<StudentOutputDto> getAllStudents(int numPage, int pageSize);
}
