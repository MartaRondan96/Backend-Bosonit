package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentOutputDto getStudentById(int id) {
        return studentRepository.findById(id).orElseThrow()
                .studentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto addStudent(StudentInputDto persona) {
        return null;
    }

    @Override
    public StudentOutputDto updateStudent(StudentInputDto persona, int id) {
        return null;
    }

    @Override
    public void deleteStudentById(int id) {

    }

    @Override
    public Iterable<StudentOutputDto> getAllStudents(int numPage, int pageSize) {
        return null;
    }
}
