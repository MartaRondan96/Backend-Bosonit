package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaService;
import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.CustomError;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws Exception {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }
    //    Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaId(@PathVariable int id) throws EntityNotFoundException {
        personaService.getPersonaById(id);
        return ResponseEntity.ok().body(personaService.getPersonaById(id));
    }
    //    Buscar por nombre de usuario (campo usuario)
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaOutputDto> getPersonaUsuario(@PathVariable String usuario) {
        try {
            return ResponseEntity.ok().body(personaService.getPersonaByUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //    Mostrar todos los registros.

    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(
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
}
