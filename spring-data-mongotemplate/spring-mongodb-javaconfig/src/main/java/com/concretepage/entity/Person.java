package com.concretepage.entity;

import org.springframework.data.annotation.Id;
public class Person { 
	@Id
	public Integer id;
	public String name;
	public Person(Integer id, String name){
		this.id = id;
		this.name=name;
	}
} 
