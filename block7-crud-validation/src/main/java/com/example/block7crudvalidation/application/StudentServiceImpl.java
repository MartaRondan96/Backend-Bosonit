package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentOutputDto getStudentById(int id) {
        if(studentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        else {
            return studentRepository.findById(id).orElseThrow()
                    .studentToStudentOutputDto();
        }
    }

    @Override
    public StudentOutputDto addStudent(StudentInputDto student) {
        return studentRepository.save(new Student(student)).studentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto updateStudent(StudentInputDto student, int id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        Student studentAct = studentRepository.findById(id).orElseThrow();

        return studentRepository.save(studentAct)
                .studentToStudentOutputDto();
    }

    @Override
    public void deleteStudentById(int id) {
        if (studentRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        else
            studentRepository.deleteById(id);
    }

    @Override
    public Iterable<StudentOutputDto> getAllStudents(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentToStudentOutputDto).toList();
    }

}
