package com.example.block12mongodb.controller;

import com.example.block12mongodb.application.PersonaDAL;
import com.example.block12mongodb.controller.dto.PersonaInputDto;
import com.example.block12mongodb.controller.dto.PersonaOutputDto;
import com.example.block12mongodb.domain.Persona;
import com.example.block12mongodb.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaDAL personDAL;
    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPerson(@RequestBody PersonaInputDto persona) {
        Persona p = new Persona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDAL.savePerson(p).personaToPersonaOutputDto());
    }

    @GetMapping
    public ResponseEntity<List<PersonaOutputDto>> getAllPersons(){
        List<PersonaOutputDto> persons = new ArrayList<>();
        for (Persona persona:personDAL.getAllPerson()){
            persons.add(persona.personaToPersonaOutputDto());
        }
        return ResponseEntity.ok(persons);
    }
    @GetMapping("/getAllPaginated")
    public ResponseEntity<List<PersonaOutputDto>> getAllPersonsPaginated(@RequestParam(defaultValue = "0", required = false) int numPages,
                                                                @RequestParam(defaultValue = "5", required = false) int pageSize) {
        List<PersonaOutputDto> personasList = new ArrayList<>();
        for (Persona persona:personDAL.getAllPersonPaginated(numPages, pageSize)){
            personasList.add(persona.personaToPersonaOutputDto());
        }
        return ResponseEntity.ok(personasList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonById(@PathVariable("id") int id) {
        try {
            Persona persona = personDAL.getPersonById(id);
            return ResponseEntity.ok(persona.personaToPersonaOutputDto());
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePerson(@PathVariable("id") int id, @RequestBody Persona updatedPerson) {
        return ResponseEntity.ok().body(personDAL.updateOnePerson(updatedPerson,id).personaToPersonaOutputDto());

    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam int id) {
        Persona p = personaRepository.findById(id);
        try {
            personDAL.deletePerson(p);
            return ResponseEntity.ok().body("Persona con id " + id + " se ha borrado.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
