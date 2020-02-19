package com.springboot.titletech.service;

import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.ParcelOwnership;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ParcelOwnershipService {

    List<ParcelOwnership> findAll();

    ParcelOwnership findById(int parcelOwnershipid);

    void save(ParcelOwnership parcelOwnership);

    void deleteById(int parcelOwnershipid);
}
