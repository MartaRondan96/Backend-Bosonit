package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaService;
import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorSimpleOutputDto;
import com.example.block7crudvalidation.domain.CustomError;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.feign.ProfesorClient;
import com.example.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    ProfesorClient profesorClient;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws Exception {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }
    //    Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonaId(@RequestParam(value = "outputType",defaultValue = "simple") String outputType,
                                                         @PathVariable int id) throws EntityNotFoundException {
        personaService.getPersonaById(id, outputType);
        return ResponseEntity.ok().body(personaService.getPersonaById(id,outputType));
    }
    //    Buscar por nombre de usuario (campo usuario)
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaOutputDto> getPersonaUsuario(@RequestParam(value = "outputType",defaultValue = "simple")String outputType,
                                                              @PathVariable String usuario) {
        try {
            return ResponseEntity.ok().body(personaService.getPersonaByUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //Mostrar todos los registros.

    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(@RequestParam(value = "outputType",defaultValue = "simple")String outputType,
            @RequestParam(defaultValue = "0", required = false) int numPages,
            @RequestParam(defaultValue = "4", required = false) int pageSize)
        {
        return personaService.getAllPersonas(numPages, pageSize);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(@RequestBody PersonaInputDto Persona, @PathVariable int id) throws EntityNotFoundException, UnprocessableEntityException {
        personaService.updatePersona(Persona,id);
        return ResponseEntity.ok().body(personaService.getPersonaById(id));
    }
    //Al borrar una persona que tiene profesion asignada borra en cascada el profesor o estudiante que tenga asignado
    //el idPersona que se va a borrar
    @DeleteMapping
    public ResponseEntity<String> deletePersonaById(@RequestParam int id) {
        try {
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body("Persona con id " + id + " se ha borrado.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomError error = ex.getError();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        CustomError error = ex.getError();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @GetMapping("/profesor/{id}")
    public ProfesorSimpleOutputDto getProfesor(@PathVariable int id) {
        ProfesorSimpleOutputDto profesorDto = profesorClient.getProfesor(id);
        return profesorDto;
    }

    @GetMapping("/buscarPersona")
    public Iterable<PersonaOutputDto> findPerson(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) LocalDate date_max,
            @RequestParam(required = false) LocalDate date_min,
            @RequestParam(required = false, defaultValue = "u") String order) {

        HashMap<String, Object> data = new HashMap<>();

        if(usuario != null) data.put("usuario",usuario);
        if(name != null) data.put("name",name);
        if(surname != null) data.put("surname",surname);
        if(date_max != null) data.put("date_max",date_max);
        if(date_min != null) data.put("date_min",date_min);
        if(!order.equals("n") && !order.equals("u")){
            order = "u";
        }

        return personaRepository.getCustomQuery(data, order);
    }


}
