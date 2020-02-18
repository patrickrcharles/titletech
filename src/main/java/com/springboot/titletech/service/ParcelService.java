package com.springboot.titletech.service;

import com.springboot.titletech.entity.Parcel;

import java.util.List;

public interface ParcelService  {

    List<Parcel> findAll();

    Parcel findById(int parcelid);

    void save(Parcel theParcel);

    void deleteById(int parcelid);
}
