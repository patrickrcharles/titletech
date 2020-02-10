package com.springboot.titletech.service;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.Person;

import java.util.List;

public interface ParcelService  {

    List<Parcel> findAll();

    Parcel findById(int personid);

    void save(Parcel theParcel);

    void deleteById(int parcelid);
}
