package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public StudentOutputFullDto getFullStudentById(int id) {
        if(studentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        else {
            return studentRepository.findById(id).orElseThrow()
                    .studentToStudentOutputFullDto();
        }
    }
    @Override
    public StudentOutputSimpleDto getSimpleStudentById(int id) {
        if(studentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        else {
            return studentRepository.findById(id).orElseThrow()
                    .studentToStudentOutputSimpleDto();
        }
    }

    @Override
    public StudentOutputFullDto addStudent(StudentInputDto studentDto) {
        Persona persona = personaRepository.findById(studentDto.getIdPersona()).orElseThrow();
        Student student = new Student(studentDto);
        student.setPersona(persona);
        return studentRepository.save(student).studentToStudentOutputFullDto();
    }

    @Override
    public StudentOutputFullDto updateStudent(StudentInputDto student, int id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        Student studentAct = studentRepository.findById(id).orElseThrow();

        return studentRepository.save(studentAct)
                .studentToStudentOutputFullDto();
    }

    @Override
    public void deleteStudentById(int id) {
        if (studentRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");

        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<StudentOutputFullDto> getAllStudents(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentToStudentOutputFullDto).toList();
    }

}
