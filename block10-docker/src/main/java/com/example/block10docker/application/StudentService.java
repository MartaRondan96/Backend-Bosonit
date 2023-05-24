package com.example.block10docker.application;

import com.example.block10docker.controller.dto.StudentInputDto;
import com.example.block10docker.controller.dto.StudentOutputFullDto;
import com.example.block10docker.controller.dto.StudentOutputSimpleDto;

import java.util.List;

public interface StudentService {
    StudentOutputFullDto getFullStudentById(int id);
    StudentOutputSimpleDto getSimpleStudentById(int id);
    StudentOutputFullDto addStudent(StudentInputDto student) throws Exception;
    StudentOutputFullDto updateStudent(StudentInputDto student, int id);
    void deleteStudentById(int id);
    Iterable<StudentOutputFullDto> getAllStudents(int numPage, int pageSize);

    StudentOutputSimpleDto addEstudiosToStudent(int id, List<Integer> idList);
    StudentOutputSimpleDto removeEstudiosToStudent(int id, List<Integer> idList);
}


