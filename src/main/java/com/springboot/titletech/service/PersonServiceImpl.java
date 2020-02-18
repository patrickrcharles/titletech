package com.springboot.titletech.service;

import java.util.List;

import com.springboot.titletech.entity.Person;
import com.springboot.titletech.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;
	
	@Autowired
	public PersonServiceImpl(PersonDAO thepersonDAO) {
		personDAO = thepersonDAO;
	}
	
	@Override
	@Transactional
	public List<Person> findAll() {
		return personDAO.findAll();
	}

	@Override
	@Transactional
	public Person findById(int theId) {
		return personDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Person thePerson) {
		personDAO.save(thePerson);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		personDAO.deleteById(theId);
	}

}






