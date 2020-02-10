package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Parcel;

import java.util.List;

public interface ParcelDAO {
    
    List<Parcel> findAll();

    Parcel findById(int theId);

    void save(Parcel theParcel);

    void deleteById(int theId);
}
