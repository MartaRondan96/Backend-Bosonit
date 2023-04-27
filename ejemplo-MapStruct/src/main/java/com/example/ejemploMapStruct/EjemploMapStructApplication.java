package com.example.ejemploMapStruct;

import jakarta.annotation.PostConstruct;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EjemploMapStructApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjemploMapStructApplication.class, args);
	}
	StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

	@PostConstruct
	public void run () {
		StudentDto studentDto = new StudentDto("StudentName", 37);
		Student student = mapper.studentDtoToStudent(studentDto);
		System.out.println("Objeto student:");
		System.out.println(student);
		System.out.println("Objeto studentDto:");
		System.out.println(studentDto);

//		Student student1 = new Student("StudentName1", 25);
//		StudentDto studentDto1 = mapper.studentToStudentDto(student1);
//		System.out.println("Objeto student1:");
//		System.out.println(student1);
//		System.out.println("Objeto studentDto1:");
//		System.out.println(studentDto1);

		StudentDetails studentDetails = new StudentDetails("StudentLastName","666-777-888");
		student.setDetails(studentDetails);
		System.out.println("Objeto studentDetails:");
		System.out.println(studentDetails);
		StudentDtoFull studentDtoFull = mapper.studentToStudentDtoFull(student);
		System.out.println("Objeto studentDtoFull:");
		System.out.println(studentDtoFull);

	}

}
