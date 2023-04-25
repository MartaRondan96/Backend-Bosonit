package com.example.springdata.controller;

import com.example.springdata.application.StudentServiceImpl;
import com.example.springdata.controller.dto.StudentInputDTO;
import com.example.springdata.controller.dto.StudentOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDTO> addStudent(@RequestBody StudentInputDTO student) {
        URI location = URI.create("/student");
        //Devuelve una respuesta 201
        return ResponseEntity.created(location).body(studentService.addStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDTO> getStudentById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@RequestParam int id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().body("student with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<StudentOutputDTO> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @PutMapping
    public ResponseEntity<StudentOutputDTO> updateStudent(@RequestBody StudentInputDTO student) {
        try {
            studentService.getStudentById(student.getId());
            return  ResponseEntity.ok().body(studentService.addStudent(student));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
