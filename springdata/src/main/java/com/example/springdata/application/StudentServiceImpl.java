package com.example.springdata.application;


import com.example.springdata.controller.dto.StudentInputDTO;
import com.example.springdata.controller.dto.StudentOutputDTO;
import com.example.springdata.domain.Student;
import com.example.springdata.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
//Las clases servicios reciben y devuelven DTOs no objetos
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public StudentOutputDTO addStudent(StudentInputDTO student) {
        return studentRepository.save(new Student(student)).studentToStudentOutputDTO();
    }

    @Override
    public StudentOutputDTO getStudentById(int id) {
        return studentRepository.findById(id).orElseThrow()
                .studentToStudentOutputDTO();
    }

    @Override
    public void deleteStudentById(int id) {
        studentRepository.findById(id).orElseThrow();
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<StudentOutputDTO> getAllStudents(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentToStudentOutputDTO).toList();
    }

    @Override
    public StudentOutputDTO updateStudent(StudentInputDTO student) {
        studentRepository.findById(student.getId()).orElseThrow();
        return studentRepository.save(new Student(student))
                .studentToStudentOutputDTO();

    }
}
