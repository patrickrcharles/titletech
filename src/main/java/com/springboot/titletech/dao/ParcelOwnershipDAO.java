package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelOwnership;

import java.util.List;

public interface ParcelOwnershipDAO {

    List<ParcelOwnership> findAll();

    ParcelOwnership findById(int theId);

    void save(ParcelOwnership theParcelOwnership);

    void deleteById(int theId);

}
