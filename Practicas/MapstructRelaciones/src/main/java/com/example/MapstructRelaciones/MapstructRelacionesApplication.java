package com.example.MapstructRelaciones;

import jakarta.annotation.PostConstruct;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapstructRelacionesApplication {
	StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

	public static void main(String[] args) {
		SpringApplication.run(MapstructRelacionesApplication.class, args);
	}
	@PostConstruct
	public void run () {
		StudentDto studentDto = new StudentDto("StudentName", 37);
		Student student = mapper.studentDtoToStudent(studentDto);
		System.out.println("Objeto student:");
		System.out.println(student);
		System.out.println("Objeto studentDto:");
		System.out.println(studentDto);

		StudentDetails studentDetails = new StudentDetails("StudentLastName","666-777-888");
		student.setDetails(studentDetails);
		System.out.println("Objeto studentDetails:");
		System.out.println(studentDetails);
		StudentDtoFull studentDtoFull = mapper.studentToStudentDtoFull(student);
		System.out.println("Objeto studentDtoFull:");
		System.out.println(studentDtoFull);

	}

}
