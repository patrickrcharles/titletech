package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ParcelDocumentDAOImpl implements ParcelDocumentDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public ParcelDocumentDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<ParcelDocument> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<ParcelDocument> theQuery =
                currentSession.createQuery("from ParcelDocument", ParcelDocument.class);

        // execute query and get result list
        List<ParcelDocument> parcelDocuments = theQuery.getResultList();

        // return the results
        return parcelDocuments;
    }

    @Override
    public ParcelDocument findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        ParcelDocument theParcel =
                currentSession.get(ParcelDocument.class, theId);

        // return the employee
        return theParcel;
    }

    @Override
    public void save(ParcelDocument theParcelDocument) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(theParcelDocument);
    }

    @Override
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from ParcelDocument where id=:parcelId");
        theQuery.setParameter("parcelId", theId);

        theQuery.executeUpdate();
    }
}
