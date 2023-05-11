package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;
import com.example.block7crudvalidation.domain.Alumnos_Estudios;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.Alumnos_EstudiosRepository;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.hibernate.IdentifierLoadAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private Alumnos_EstudiosRepository estudiosRepository;
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
    public StudentOutputFullDto addStudent(StudentInputDto studentDto) throws Exception {
        Persona persona = personaRepository.findById(studentDto.getIdPersona()).orElseThrow();
        if(persona.getProfesion()!=null)
            throw new Exception("La persona ya tiene profesion asignada");
        persona.setProfesion("Estudiante");
        Student student = new Student(studentDto);
        student.setPersona(persona);
        return studentRepository.save(student).studentToStudentOutputFullDto();
    }

    @Override
    public StudentOutputFullDto updateStudent(StudentInputDto studentDto, int id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        Student studentAct = studentRepository.findById(id).orElseThrow();
        studentAct.setNum_hours_week(studentDto.getNum_hours_week());
        studentAct.setBranch(studentDto.getBranch());
        studentAct.setComments(studentDto.getComments());
        return studentRepository.save(studentAct)
                .studentToStudentOutputFullDto();
    }

    @Override
    public void deleteStudentById(int id) {
        if (studentRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        Student student = studentRepository.findById(id).orElseThrow();
        Persona persona = personaRepository.findById(student.getPersona().getId()).orElseThrow();
        persona.setProfesion("null");
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<StudentOutputFullDto> getAllStudents(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentToStudentOutputFullDto).toList();
    }

    @Override
    public StudentOutputSimpleDto addEstudiosToStudent(int id, List<Integer> idList) {
        Alumnos_Estudios estudio;
        Set<Alumnos_Estudios> estudiosList = new HashSet<>();
        for (Integer i : idList) {
            estudio = estudiosRepository.findById(i).orElseThrow();
            estudiosList.add(estudio);
        }
        Student st = studentRepository.findById(id).orElseThrow();
        st.setEstudios(estudiosList);
        return studentRepository.save(st)
                .studentToStudentOutputSimpleDto();
    }
    @Override
    public StudentOutputSimpleDto removeEstudiosToStudent(int id, List<Integer> idList) {
       return null;
    }
}
