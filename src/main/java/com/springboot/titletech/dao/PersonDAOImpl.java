package com.springboot.titletech.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.springboot.titletech.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl implements PersonDAO {

	// define field for entitymanager
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public PersonDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}


	@Override
	public List<Person> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Person> theQuery =
				currentSession.createQuery("from Person", Person.class);
		
		// execute query and get result list
		List<Person> persons = theQuery.getResultList();
		
		// return the results		
		return persons;
	}


	@Override
	public Person findById(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Person thePerson =
				currentSession.get(Person.class, theId);
		
		// return the employee
		return thePerson;
	}


	@Override
	public void save(Person person) {

		System.out.println("PersonDAOImpl :: save()");
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(person);
	}


	@Override
	public void deleteById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery(
						"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}







