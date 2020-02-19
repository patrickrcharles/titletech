package com.springboot.titletech.service;

import com.springboot.titletech.dao.ParcelDocumentDAO;
import com.springboot.titletech.entity.ParcelDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParcelDocumentServiceImpl implements ParcelDocumentService {

    private ParcelDocumentDAO parcelDocumentDAO;

    @Autowired
    public ParcelDocumentServiceImpl(ParcelDocumentDAO theparcelDocumentDAO) {
        parcelDocumentDAO = theparcelDocumentDAO;
    }

    @Override
    @Transactional
    public List<ParcelDocument> findAll() {
        return parcelDocumentDAO.findAll();
    }

    @Override
    @Transactional
    public ParcelDocument findById(int theId) {
        return parcelDocumentDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(ParcelDocument theParcelDocument) {
        parcelDocumentDAO.save(theParcelDocument);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        parcelDocumentDAO.deleteById(theId);
    }
}
