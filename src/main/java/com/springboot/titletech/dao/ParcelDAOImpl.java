package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ParcelDAOImpl implements ParcelDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public ParcelDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public List<Parcel> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Parcel> theQuery =
                currentSession.createQuery("from Parcel", Parcel.class);

        // execute query and get result list
        List<Parcel> parcels = theQuery.getResultList();

        // return the results
        return parcels;
    }

    @Override
    public Parcel findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        Parcel theParcel =
                currentSession.get(Parcel.class, theId);

        // return the employee
        return theParcel;
    }

    @Override
    public List<Person> findCurrentOwnerByParcelId(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Person> theQuery =
                currentSession.createQuery("from Person where parcelid=:personid and is_current_owner = 1", Person.class);

        theQuery.setParameter("personid", theId);

        // execute query and get result list
        List<Person> persons = theQuery.getResultList();

        // return the results
        return persons;
    }

    @Override
    public List<Person> findPreviousOwnerByParcelId(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Person> theQuery =
                currentSession.createQuery("from Person where parcelid=:personid and is_current_owner = 0", Person.class);

        theQuery.setParameter("personid", theId);

        // execute query and get result list
        List<Person> persons = theQuery.getResultList();

        // return the results
        return persons;
    }


    @Override
    public void save(Parcel parcel) {

        System.out.println("ParcelDAOImpl :: save()");
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(parcel);
    }


    @Override
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from Parcel where id=:parcelId");
        theQuery.setParameter("parcelId", theId);

        theQuery.executeUpdate();
    }

}