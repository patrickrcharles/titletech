package com.springboot.titletech.service;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;

import java.util.List;

public interface ParcelService  {

    List<Parcel> findAll();

    Parcel findById(int parcelid);

    List<Person> findCurrentOwnerByParcelId(int theId);

    List<Person> findPreviousOwnerByParcelId(int theId);

    List<ParcelOwnership> findParcelHistory(int theId);

    void save(Parcel theParcel);

    void deleteById(int parcelid);
}
