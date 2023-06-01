package com.concretepage;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.concretepage.config.MongoDBConfig;
import com.concretepage.entity.Student;
import com.concretepage.repository.StudentRepository;

public class MongoRepositoryTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MongoDBConfig.class);
		ctx.refresh();
		StudentRepository repository = ctx.getBean(StudentRepository.class);

		repository.deleteAll();

		Student lakshmi = new Student(101, "Lakshmi", 22);
		Student parvati = new Student(102, "Parvati", 22);
		Student saraswati = new Student(103, "Saraswati", 24);

		repository.insert(lakshmi);
		repository.insert(parvati);
		repository.insert(saraswati);

		List<Student> list = repository.getStudentByAge(22);
		list.forEach(std -> System.out.println(std.getName() + " - " + std.getAge()));

		ctx.registerShutdownHook();
		ctx.close();
	}
}