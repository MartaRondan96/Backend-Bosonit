package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.StudentService;
import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Iterable<StudentOutputFullDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int numPages,
            @RequestParam(defaultValue = "4", required = false) int pageSize)
    {
        return studentService.getAllStudents(numPages, pageSize);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentId(@RequestParam(value = "outputType",defaultValue = "simple")String outputType,
                                                             @PathVariable int id){
        if(outputType.equalsIgnoreCase("full")){
            studentService.getFullStudentById(id);
            return ResponseEntity.ok().body(studentService.getFullStudentById(id));
        }
        else{studentService.getSimpleStudentById(id);
            return ResponseEntity.ok().body(studentService.getSimpleStudentById(id));
        }
    }

    @PostMapping
    public ResponseEntity<StudentOutputFullDto> addStudent(@Valid @RequestBody StudentInputDto student) throws Exception {
        URI location = URI.create("/student");
        return ResponseEntity.created(location).body(studentService.addStudent(student));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentOutputFullDto> updateStudent (@Valid @RequestBody StudentInputDto student, @PathVariable int id) throws Exception {
        studentService.updateStudent(student,id);
        return  ResponseEntity.ok().body(studentService.getFullStudentById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent (@RequestParam int id){
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().body("Student con id " + id + " eliminado.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addAsignatura/{idStudent}")
    public ResponseEntity<StudentOutputSimpleDto> addEstudiosToStudent(@PathVariable int idStudent, @RequestParam("IdList") List<Integer> IdList){
        try {
            studentService.addEstudiosToStudent(idStudent, IdList);
            return ResponseEntity.ok().body(studentService.getSimpleStudentById(idStudent));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/removeAsignatura/{idStudent}")
    public ResponseEntity<StudentOutputSimpleDto> removeEstudiosToStudent(@PathVariable int idStudent, @RequestParam("IdList") List<Integer> IdList){
        try {
            studentService.removeEstudiosToStudent(idStudent, IdList);
            return ResponseEntity.ok().body(studentService.getSimpleStudentById(idStudent));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
