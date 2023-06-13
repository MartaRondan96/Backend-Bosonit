package com.example.block7crud;

import com.example.block7crud.application.PersonaServiceImpl;
import com.example.block7crud.controller.dto.PersonaInputDTO;
import com.example.block7crud.controller.dto.PersonaOutputDTO;
import com.example.block7crud.domain.Persona;
import com.example.block7crud.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonaServiceImplTest {
    @Mock
    private PersonaRepository personaRepository;
    @InjectMocks
    private PersonaServiceImpl personaService;
    private Persona p;
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        p = new Persona(new PersonaInputDTO(1, "Marta", 26, "San Fernando"));

    }
    @Test
    public void testAddPersona() {
        PersonaInputDTO personaInput = new PersonaInputDTO();
        personaInput.setNombre("Juan");
        personaInput.setPoblacion("cadiz");
        personaInput.setEdad(30);

        Persona persona = new Persona();
        persona.setId(1);
        persona.setNombre(personaInput.getNombre());
        persona.setEdad(personaInput.getEdad());
        persona.setPoblacion(personaInput.getPoblacion());

        when(personaRepository.save(any(Persona.class))).thenReturn(persona);
        PersonaOutputDTO result = personaService.addPersona(personaInput);
        verify(personaRepository, times(1)).save(any(Persona.class));
        assertEquals(1, result.getId());
        assertEquals(personaInput.getNombre(), result.getNombre());
    }
    @Test
    public void getPersonaByIdTest(){

        when(personaRepository.findById(p.getId())).thenReturn(java.util.Optional.of(p));

        PersonaOutputDTO result = personaService.getPersonaById(p.getId());

        assertEquals(p.getId(), result.getId());
    }
    @Test
    public void getPersonaById_ExceptionTest(){
        when(personaRepository.findById(p.getId())).thenReturn(java.util.Optional.of(p));

        PersonaOutputDTO result = personaService.getPersonaById(p.getId());

        assertNotEquals(2, result.getId());
    }

    @Test
    public void getPersonaByNombreTest(){
        when(personaRepository.findByNombre(p.getNombre())).thenReturn(java.util.Optional.of(p));

        PersonaOutputDTO result = personaService.getPersonaByNombre(p.getNombre());

        assertEquals(p.getNombre(), result.getNombre());
    }
    @Test
    public void getPersonaByNombre_ExceptionTest(){
        when(personaRepository.findByNombre(p.getNombre())).thenReturn(java.util.Optional.of(p));

        PersonaOutputDTO result = personaService.getPersonaByNombre(p.getNombre());

        assertNotEquals("null", result.getNombre());
    }

    @Test
    public void getAllPersonsTest() {

        int numPages = 0;
        int pageSize = 10;
        Persona p1=new Persona(new PersonaInputDTO(1, "Marta", 26, "San Fernando"));
        Persona p2=new Persona(new PersonaInputDTO(12, "Marta", 26, "San Fernando"));
        p1.setId(1);
        p2.setId(2);

        List<Persona> personas = Arrays.asList(
                p1,
                p2
        );

        Page<Persona> personaPage = new PageImpl<>(personas);

        when(personaRepository.findAll(any(Pageable.class))).thenReturn(personaPage);

        Iterable<PersonaOutputDTO> result = personaService.getAllPersonas(numPages, pageSize);

        List<PersonaOutputDTO> expected = Arrays.asList(
                p1.personaToPersonaOutputDTO(),
                p2.personaToPersonaOutputDTO()
        );
        assertEquals(expected, result);

        verify(personaRepository).findAll(PageRequest.of(numPages, pageSize));
    }
    @Test
    public void updatePersonTest() {
        Persona persona1 = new Persona(new PersonaInputDTO( 1,"name",25,"city"));
        PersonaInputDTO personaInputDto =new PersonaInputDTO( 2,"name",25,"city");
        when(personaRepository.findById(persona1.getId())).thenReturn(Optional.of(persona1));
        when(personaRepository.save(persona1)).thenReturn(persona1);
        PersonaOutputDTO persona2 = personaService.updatePersona(personaInputDto,persona1.getId());

        assertNotNull(persona2);
        assertEquals(persona1.personaToPersonaOutputDTO(),persona2);
        assertEquals(persona1.getNombre(), persona2.getNombre());
    }

    @Test
    public void testDeletePersonaById() throws EntityNotFoundException {
        when(personaRepository.findById(p.getId())).thenReturn(Optional.of(p));
        personaService.deletePersonaById(p.getId());
        verify(personaRepository, times(1)).deleteById(p.getId());
    }

}
