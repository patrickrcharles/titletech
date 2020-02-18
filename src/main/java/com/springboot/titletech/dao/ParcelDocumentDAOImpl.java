package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

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
                currentSession.createQuery("from Parcel_document", ParcelDocument.class);

        // execute query and get result list
        List<ParcelDocument> parcelDocuments = theQuery.getResultList();

        // return the results
        return parcelDocuments;
    }

    @Override
    public Parcel findById(int theId) {
        return null;
    }

    @Override
    public void save(ParcelDocument theParcelDocument) {

    }

    @Override
    public void deleteById(int theId) {

    }
}
