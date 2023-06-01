package com.concretepage.entity;

import org.springframework.data.annotation.Id;
public class Student { 
	@Id
	private Integer id;
	private String name;
	private Integer age;
	public Student(Integer id, String name, Integer age){
		this.id = id;
		this.name=name;
		this.age=age;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getAge() {
		return age;
	}
	
} 
