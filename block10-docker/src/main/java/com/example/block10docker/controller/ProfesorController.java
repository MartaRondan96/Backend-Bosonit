package com.example.block10docker.controller;

import com.example.block10docker.application.ProfesorService;
import com.example.block10docker.controller.dto.ProfesorInputDto;
import com.example.block10docker.controller.dto.ProfesorFullOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorFullOutputDto> getProfesorId(@PathVariable int id){
        profesorService.getProfesorById(id);
        return ResponseEntity.ok().body(profesorService.getProfesorById(id));
    }

    @GetMapping
    public Iterable<ProfesorFullOutputDto> getAllProfesor(@RequestParam(defaultValue = "0", required = false) int numPages,
                                                            @RequestParam(defaultValue = "4", required = false) int pageSize){
        return profesorService.getAllProfesors(numPages, pageSize);
    }

    @PostMapping
    public ResponseEntity<ProfesorFullOutputDto> addProfesor(@Valid @RequestBody ProfesorInputDto profesor) throws Exception {
        URI location = URI.create("/profesor");
        return ResponseEntity.created(location).body(profesorService.addProfesor(profesor));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorFullOutputDto> updateStudent (@Valid @RequestBody ProfesorInputDto profesor, @PathVariable int id){
        profesorService.updateProfesor(profesor,id);
        return ResponseEntity.ok().body(profesorService.getProfesorById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent (@RequestParam int id){
        try {
            profesorService.deleteProfesorById(id);
            return ResponseEntity.ok().body("Profesor con id " + id + " eliminado.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
