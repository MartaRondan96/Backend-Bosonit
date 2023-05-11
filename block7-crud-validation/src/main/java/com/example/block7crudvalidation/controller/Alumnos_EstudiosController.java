package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.Alumnos_EstudiosService;
import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosInputDto;
import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/estudios")
public class Alumnos_EstudiosController {
    @Autowired
    private Alumnos_EstudiosService estudiosService;
    @GetMapping
    public Iterable<Alumnos_EstudiosOutputDto> getAllEstudios(
            @RequestParam(defaultValue = "0", required = false) int numPages,
            @RequestParam(defaultValue = "4", required = false) int pageSize)
    {
        return estudiosService.getAllEstudios(numPages, pageSize);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Alumnos_EstudiosOutputDto> getEstudioId(@PathVariable int id){
        estudiosService.getEstudioById(id);
        return ResponseEntity.ok().body(estudiosService.getEstudioById(id));
    }

    @PostMapping
    public ResponseEntity<Alumnos_EstudiosOutputDto> addStudent(@Valid @RequestBody Alumnos_EstudiosInputDto estudio){
        URI location = URI.create("/estudio");
        return ResponseEntity.created(location).body(estudiosService.addEstudio(estudio));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Alumnos_EstudiosOutputDto> updateStudent (@Valid @RequestBody Alumnos_EstudiosInputDto estudio, @PathVariable int id){
        estudiosService.updateEstudio(estudio,id);
        return ResponseEntity.ok().body(estudiosService.getEstudioById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent (@RequestParam int id){
        try {
            estudiosService.deleteEstudioById(id);
            return ResponseEntity.ok().body("Estudio con id " + id + " eliminado.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputSimpleDto> getAsignaturasStudent(@PathVariable int id) {
        return new ResponseEntity<>(estudiosService.getListAsignaturasByStudent(id), HttpStatus.OK);
    }
}
