package com.concretepage;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.concretepage.config.MongoDBConfig;
import com.concretepage.entity.Student;

public class MongoTemplateTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MongoDBConfig.class);
		ctx.refresh();
		MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);

		mongoTemplate.dropCollection(Student.class);

		Student ram = new Student(101, "Ram", 19);
		Student shyam = new Student(102, "Shyam", 19);
		Student mohan = new Student(103, "Mohan", 20);
		mongoTemplate.insert(Arrays.asList(ram, shyam, mohan), Student.class);

		Query query = new Query();
		query.addCriteria(Criteria.where("age").is(19));
		List<Student> list = mongoTemplate.find(query, Student.class, "student");
		list.forEach(std -> System.out.println(std.getName() + " - " + std.getAge()));
		
		ctx.registerShutdownHook();
		ctx.close();		
	}
}