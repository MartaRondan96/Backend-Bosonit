package com.example.block7crudvalidation;
import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonaServiceImplTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPersonaById() throws Exception {
        int id = 1;
        Persona persona = new Persona();
        persona.setId(id);
        when(personaRepository.findById(id)).thenReturn(java.util.Optional.of(persona));

        PersonaOutputDto result = personaService.getPersonaById(id);

        assertEquals(id, result.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetPersonaById_EntityNotFoundException() throws EntityNotFoundException {
        int id = 1;
        when(personaRepository.findById(id)).thenReturn(java.util.Optional.empty());

        personaService.getPersonaById(id);
    }

    @Test
    public void testGetPersonaByUsuario() throws Exception {
        String usuario = "Marta";
        Persona persona = new Persona();
        persona.setUsuario(usuario);
        when(personaRepository.findByUsuario(usuario)).thenReturn(java.util.Optional.of(persona));

        PersonaOutputDto result = personaService.getPersonaByUsuario(usuario);

        assertEquals(usuario, result.getUsuario());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetPersonaByUsuario_EntityNotFoundException() throws EntityNotFoundException {
        String usuario = "Marta";
        when(personaRepository.findByUsuario(usuario)).thenReturn(java.util.Optional.empty());

        personaService.getPersonaByUsuario(usuario);
    }

    @Test
    public void testAddPersona() throws Exception {

        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setUsuario("MartaR");
        personaInputDto.setPassword("password");
        personaInputDto.setName("Marta");
        personaInputDto.setCompany_email("email@company.com");
        personaInputDto.setPersonal_email("email@personal.com");
        personaInputDto.setCity("Cadiz");
        personaInputDto.setActive(true);
        personaInputDto.setCreate_date(new Date());

        when(personaRepository.save(any(Persona.class))).thenReturn(new Persona());
        PersonaOutputDto result = personaService.addPersona(personaInputDto);
        assertNotNull(result);
        verify(personaRepository).save(any(Persona.class));
    }
    @Test
    public void testUpdatePersona() throws Exception {
        Persona persona1 = new Persona(new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));
        PersonaInputDto personaInputDto =new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date());
        when(personaRepository.findById(persona1.getId())).thenReturn(Optional.of(persona1));
        when(personaRepository.save(persona1)).thenReturn(persona1);
        PersonaOutputDto persona2 = personaService.updatePersona(personaInputDto,persona1.getId()); //IMPL

        assertNotNull(persona2);
        assertEquals(persona1.personaToPersonaOutputDTO(),persona2);
        assertEquals(persona1.getName(), persona2.getName());

    }

    @Test
    public void testDeletePersonaById() throws EntityNotFoundException {
        int id = 1;
        Persona persona1 = new Persona(new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));

        persona1.setId(id);
        when(personaRepository.findById(id)).thenReturn(Optional.of(persona1));
        personaService.deletePersonaById(id);
        verify(personaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetAllPersonas() {

        int numPages = 0;
        int pageSize = 10;
        Persona p1=new Persona(new PersonaInputDto( "persona 1","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));
        Persona p2=new Persona(new PersonaInputDto( "persona 2","pass","name","surname","company_email","personal_email","city",true,new Date(),"image_url",new Date()));
        p1.setId(1);
        p2.setId(2);

        List<Persona> personas = Arrays.asList(
                p1,
                p2
        );

        Page<Persona> personaPage = new PageImpl<>(personas);

        when(personaRepository.findAll(any(Pageable.class))).thenReturn(personaPage);

        Iterable<PersonaOutputDto> result = personaService.getAllPersonas(numPages, pageSize);

        List<PersonaOutputDto> expected = Arrays.asList(
                p1.personaToPersonaOutputDTO(),
                p2.personaToPersonaOutputDTO()
        );
        assertEquals(expected, result);

        verify(personaRepository).findAll(PageRequest.of(numPages, pageSize));
    }
}
