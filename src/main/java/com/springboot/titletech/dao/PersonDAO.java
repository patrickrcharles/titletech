package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Person;

import java.util.List;

public interface PersonDAO {

	public List<Person> findAll();
	
	public Person findById(int theId);
	
	public void save(Person thePerson);
	
	public void deleteById(int theId);
	
}
