package com.springboot.titletech.repository;

import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ParcelRepositoryImpl implements ParcelResositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

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
    public List<ParcelOwnership> findParcelHistory(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<ParcelOwnership> theQuery =
                currentSession.createQuery("from ParcelOwnership where " +
                        "parcelid=:id", ParcelOwnership.class);

        theQuery.setParameter("id", theId);

        // execute query and get result list
        List<ParcelOwnership> parcelOwnership = theQuery.getResultList();

        // return the results
        return parcelOwnership;
    }
}

