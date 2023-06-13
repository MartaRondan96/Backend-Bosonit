package com.example.block7crudvalidation;

import com.example.block7crudvalidation.application.StudentServiceImpl;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetFullStudentById() throws Exception {
        int idPersona = 1;
        Persona persona = new Persona();
        persona.setId(idPersona);
        when(personaRepository.save(any(Persona.class))).thenReturn(new Persona());
        int id = 1;
        Student student = new Student();
        student.setId(id);
        student.setPersona(persona);
        when(studentRepository.findById(id)).thenReturn(java.util.Optional.of(student));

        StudentOutputFullDto result = studentService.getFullStudentById(id);

        assertTrue(id == result.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetFullStudentById_EntityNotFoundException() throws EntityNotFoundException {
        int id = 1;
        when(studentRepository.findById(id)).thenReturn(java.util.Optional.empty());

        studentService.getFullStudentById(id);
    }
}
