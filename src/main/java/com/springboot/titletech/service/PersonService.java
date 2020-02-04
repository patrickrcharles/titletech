package com.springboot.titletech.service;

import com.springboot.titletech.entity.Person;

import java.util.List;

public interface PersonService {

	public List<Person> findAll();
	
	public Person findById(int theId);
	
	public void save(Person theEmployee);
	
	public void deleteById(int theId);
	
}
