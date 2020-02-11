package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;

import java.util.List;

public interface ParcelDocumentDAO {

    List<ParcelDocument> findAll();

    Parcel findById(int theId);

    void save(ParcelDocument theParcelDocument);

    void deleteById(int theId);
}
