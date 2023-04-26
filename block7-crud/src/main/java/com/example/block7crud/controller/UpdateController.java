package com.example.block7crud.controller;

import com.example.block7crud.application.PersonaServiceImpl;
import com.example.block7crud.controller.dto.PersonaInputDTO;
import com.example.block7crud.controller.dto.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class UpdateController {
    @Autowired
    PersonaServiceImpl personaService;
    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDTO> updatePersona(@RequestBody PersonaInputDTO Persona, @PathVariable int id) {
            personaService.updatePersona(Persona,id);
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
    }
}
