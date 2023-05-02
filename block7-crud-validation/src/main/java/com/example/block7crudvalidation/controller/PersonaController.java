package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws Exception {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }
    //    Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaId(@PathVariable int id){
        personaService.getPersonaById(id);
        return ResponseEntity.ok().body(personaService.getPersonaById(id));
    }
    //    Buscar por nombre de usuario (campo usuario)
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaOutputDto> getPersonaName(@PathVariable String usuario) {
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
    public ResponseEntity<PersonaOutputDto> updatePersona(@RequestBody PersonaInputDto Persona, @PathVariable int id) {
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
}
