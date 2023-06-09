package com.example.MapstructRelaciones;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface StudentMapper {
    @Mapping(target="name", source="studentName")
    @Mapping(target="age", source="studentAge")
    StudentDto studentToStudentDto(Student student);

    @Mapping(target="studentName", source="name")
    @Mapping(target="studentAge", source="age")
    Student studentDtoToStudent(StudentDto studentDto);

    @Mapping(target="name", source="studentName")
    @Mapping(target="age", source="studentAge")
    @Mapping(target="lastName", source="student.details.lastName")
    @Mapping(target="phoneNumber", source="student.details.phoneNumber")
    StudentDtoFull studentToStudentDtoFull(Student student);
}
