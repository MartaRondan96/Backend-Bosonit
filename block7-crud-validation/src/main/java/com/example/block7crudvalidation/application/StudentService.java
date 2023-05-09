package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;

public interface StudentService {
    StudentOutputFullDto getFullStudentById(int id);
    StudentOutputSimpleDto getSimpleStudentById(int id);
    StudentOutputFullDto addStudent(StudentInputDto student);
    StudentOutputFullDto updateStudent(StudentInputDto student, int id);
    void deleteStudentById(int id);
    Iterable<StudentOutputFullDto> getAllStudents(int numPage, int pageSize);
}


