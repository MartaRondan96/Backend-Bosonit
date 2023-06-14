package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonaInputDTO;
import com.example.block7crud.controller.dto.PersonaOutputDTO;
import com.example.block7crud.domain.Persona;
import com.example.block7crud.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonaServiceImplTest {
    @Mock
    private PersonaRepository personaRepository;
    @InjectMocks
    private PersonaServiceImpl personaService;
    private Persona p;

    public PersonaServiceImplTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.p = new Persona(new PersonaInputDTO(1, "Marta", 26, "San Fernando"));
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
        Mockito.when((Persona)this.personaRepository.save((Persona) ArgumentMatchers.any(Persona.class))).thenReturn(persona);
        PersonaOutputDTO result = this.personaService.addPersona(personaInput);
        ((PersonaRepository)Mockito.verify(this.personaRepository, Mockito.times(1))).save((Persona)ArgumentMatchers.any(Persona.class));
        Assert.assertEquals(1L, (long)result.getId());
        Assert.assertEquals(personaInput.getNombre(), result.getNombre());
    }

    @Test
    public void getPersonaByIdTest() {
        Mockito.when(this.personaRepository.findById(this.p.getId())).thenReturn(Optional.of(this.p));
        PersonaOutputDTO result = this.personaService.getPersonaById(this.p.getId());
        Assert.assertEquals((long)this.p.getId(), (long)result.getId());
    }

    @Test
    public void getPersonaById_ExceptionTest() {
        Mockito.when(this.personaRepository.findById(this.p.getId())).thenReturn(Optional.of(this.p));
        PersonaOutputDTO result = this.personaService.getPersonaById(this.p.getId());
        Assertions.assertNotEquals(2, result.getId());
    }

    @Test
    public void getPersonaByNombreTest() {
        Mockito.when(this.personaRepository.findByNombre(this.p.getNombre())).thenReturn(Optional.of(this.p));
        PersonaOutputDTO result = this.personaService.getPersonaByNombre(this.p.getNombre());
        Assert.assertEquals(this.p.getNombre(), result.getNombre());
    }

    @Test
    public void getPersonaByNombre_ExceptionTest() {
        Mockito.when(this.personaRepository.findByNombre(this.p.getNombre())).thenReturn(Optional.of(this.p));
        PersonaOutputDTO result = this.personaService.getPersonaByNombre(this.p.getNombre());
        Assertions.assertNotEquals("null", result.getNombre());
    }

    @Test
    public void getAllPersonsTest() {
        int numPages = 0;
        int pageSize = 10;
        Persona p1 = new Persona(new PersonaInputDTO(1, "Marta", 26, "San Fernando"));
        Persona p2 = new Persona(new PersonaInputDTO(12, "Marta", 26, "San Fernando"));
        p1.setId(1);
        p2.setId(2);
        List<Persona> personas = Arrays.asList(p1, p2);
        Page<Persona> personaPage = new PageImpl(personas);
        Mockito.when(this.personaRepository.findAll((Pageable)ArgumentMatchers.any(Pageable.class))).thenReturn(personaPage);
        Iterable<PersonaOutputDTO> result = this.personaService.getAllPersonas(numPages, pageSize);
        List<PersonaOutputDTO> expected = Arrays.asList(p1.personaToPersonaOutputDTO(), p2.personaToPersonaOutputDTO());
        Assert.assertEquals(expected, result);
        ((PersonaRepository)Mockito.verify(this.personaRepository)).findAll(PageRequest.of(numPages, pageSize));
    }

    @Test
    public void updatePersonTest() {
        Persona persona1 = new Persona(new PersonaInputDTO(1, "name", 25, "city"));
        PersonaInputDTO personaInputDto = new PersonaInputDTO(2, "name", 25, "city");
        Mockito.when(this.personaRepository.findById(persona1.getId())).thenReturn(Optional.of(persona1));
        Mockito.when((Persona)this.personaRepository.save(persona1)).thenReturn(persona1);
        PersonaOutputDTO persona2 = this.personaService.updatePersona(personaInputDto, persona1.getId());
        Assertions.assertNotNull(persona2);
        Assert.assertEquals(persona1.personaToPersonaOutputDTO(), persona2);
        Assert.assertEquals(persona1.getNombre(), persona2.getNombre());
    }

    @Test
    public void testDeletePersonaById() throws EntityNotFoundException {
        Mockito.when(this.personaRepository.findById(this.p.getId())).thenReturn(Optional.of(this.p));
        this.personaService.deletePersonaById(this.p.getId());
        ((PersonaRepository)Mockito.verify(this.personaRepository, Mockito.times(1))).deleteById(this.p.getId());
    }
}
