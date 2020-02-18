package com.springboot.titletech.dao;

import com.springboot.titletech.entity.ParcelOwnership;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ParcelOwnershipDAOimpl implements ParcelOwnershipDAO{

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public ParcelOwnershipDAOimpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public List<ParcelOwnership> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<ParcelOwnership> theQuery =
                currentSession.createQuery("from Parcel", ParcelOwnership.class);

        // execute query and get result list
        List<ParcelOwnership> parcelOwnerships = theQuery.getResultList();

        // return the results
        return parcelOwnerships;
    }


    @Override
    public ParcelOwnership findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        ParcelOwnership theParcelOwnership =
                currentSession.get(ParcelOwnership.class, theId);

        // return the employee
        return theParcelOwnership;
    }


    @Override
    public void save(ParcelOwnership parcelOwnership) {

        System.out.println("\nParcelOwnershipDAOImpl :: save()");
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save
        currentSession.saveOrUpdate(parcelOwnership);
    }

    @Override
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from ParcelOwnership where id=:parcelId");
        theQuery.setParameter("parcelId", theId);

        theQuery.executeUpdate();
    }
}
