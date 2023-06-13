package com.example.block7crudvalidation;

import com.example.block7crudvalidation.application.ProfesorService;
import com.example.block7crudvalidation.application.ProfesorServiceImpl;
import com.example.block7crudvalidation.controller.dto.ProfesorFullOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProfesorServiceImplTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProfesorById() throws Exception {
        int idPersona = 1;
        Persona persona = new Persona();
        persona.setId(idPersona);
        when(personaRepository.save(any(Persona.class))).thenReturn(new Persona());
        int id = 1;
        Profesor profesor = new Profesor();
        profesor.setId(id);
        profesor.setPersona(persona);
        when(profesorRepository.findById(id)).thenReturn(java.util.Optional.of(profesor));

        ProfesorFullOutputDto result = profesorService.getProfesorById(id);

        assertTrue(id == result.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetProfesorById_EntityNotFoundException() throws EntityNotFoundException {
        int id = 1;
        when(profesorRepository.findById(id)).thenReturn(java.util.Optional.empty());

        profesorService.getProfesorById(id);
    }

    @Test
    public void testAddProfesor() throws Exception {
        // Arrange
        ProfesorInputDto profesorDto = new ProfesorInputDto();
        profesorDto.setIdPersona(1);

        Persona persona = new Persona();
        persona.setProfesion(null);
        persona.setId(profesorDto.getIdPersona());

        Profesor profesor = new Profesor(profesorDto);
        profesor.setPersona(persona);

        ProfesorFullOutputDto expectedOutputDto = new ProfesorFullOutputDto();

        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
        profesorRepository.save(profesor);

        ProfesorFullOutputDto pfdto =profesor.profesorToProfesorFullOutputDto();

        when(profesorService.addProfesor(profesorDto)).thenReturn(pfdto);

        assertEquals("Profesor", persona.getProfesion());
        verify(personaRepository, times(1)).findById(1);
        verify(profesorRepository, times(1)).save(profesor);
    }
//    @Test
//    public void testUpdatePersona() throws Exception {
//        Persona persona1 = new Persona(new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));
//        PersonaInputDto personaInputDto =new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date());
//        when(profesorRepository.findById(persona1.getId())).thenReturn(Optional.of(persona1));
//        when(profesorRepository.save(persona1)).thenReturn(persona1);
//        PersonaOutputDto persona2 = profesorService.updatePersona(personaInputDto,persona1.getId()); //IMPL
//
//        assertNotNull(persona2);
//        assertEquals(persona1.personaToPersonaOutputDTO(),persona2);
//        assertEquals(persona1.getName(), persona2.getName());
//
//    }
//
//    @Test
//    public void testDeletePersonaById() throws EntityNotFoundException {
//        int id = 1;
//        Persona persona1 = new Persona(new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));
//
//        persona1.setId(id);
//        when(profesorRepository.findById(id)).thenReturn(Optional.of(persona1));
//        profesorService.deletePersonaById(id);
//        verify(profesorRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    public void testGetAllPersonas() {
//
//        int numPages = 0;
//        int pageSize = 10;
//        Profesor p1=new Persona(new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));
//        Persona p2=new Persona(new PersonaInputDto( "persona 2","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));
//        p1.setId(1);
//        p2.setId(2);
//
//        List<Persona> personas = Arrays.asList(
//                p1,
//                p2
//        );
//
//        Page<Persona> personaPage = new PageImpl<>(personas);
//
//        when(profesorRepository.findAll(any(Pageable.class))).thenReturn(personaPage);
//
//        Iterable<PersonaOutputDto> result = profesorService.getAllPersonas(numPages, pageSize);
//
//        List<PersonaOutputDto> expected = Arrays.asList(
//                p1.personaToPersonaOutputDTO(),
//                p2.personaToPersonaOutputDTO()
//        );
//        assertEquals(expected, result);
//
//        verify(profesorRepository).findAll(PageRequest.of(numPages, pageSize));
//    }
}
