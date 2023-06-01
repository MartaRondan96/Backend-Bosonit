package com.concretepage.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.concretepage.entity.Student;

@Component
public interface StudentRepository extends MongoRepository<Student, Integer> {
    Student getStudentById(int id);
    List<Student> getStudentByAge(int age);
}
