package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.Person;

import java.util.List;

public interface ParcelDAO {

    List<Parcel> findAll();

    Parcel findById(int theId);

    List<Person> findCurrentOwnerByParcelId(int theId);

    List<Person> findPreviousOwnerByParcelId(int theId);

    void save(Parcel theParcel);

    void deleteById(int theId);
}
