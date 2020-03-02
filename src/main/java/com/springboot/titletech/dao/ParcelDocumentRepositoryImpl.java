package com.springboot.titletech.dao;

import com.springboot.titletech.entity.ParcelDocument;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ParcelDocumentRepositoryImpl implements ParcelDocumentRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ParcelDocument> findParcelDocumentsByParcelID(int parcelId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<ParcelDocument> theQuery =
                currentSession.createQuery("from ParcelDocument where parcelid=:parcelid ", ParcelDocument.class);

        theQuery.setParameter("parcelid", parcelId);

        // execute query and get result list
        List<ParcelDocument> parcelDocuments = theQuery.getResultList();

        // return the results
        return parcelDocuments;
    }
}
