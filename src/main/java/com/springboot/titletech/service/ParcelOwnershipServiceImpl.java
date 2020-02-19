package com.springboot.titletech.service;

import com.springboot.titletech.dao.ParcelOwnershipDAO;
import com.springboot.titletech.entity.ParcelOwnership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParcelOwnershipServiceImpl implements ParcelOwnershipService {

    ParcelOwnershipDAO parcelOwnershipDAO;

    @Autowired
    ParcelOwnershipServiceImpl(ParcelOwnershipDAO theParcelOwnershipDAO){
        parcelOwnershipDAO = theParcelOwnershipDAO;
    }

    @Override
    @Transactional
    public List<ParcelOwnership> findAll() {
        System.out.print("\nparcelOwnershipService :: List<List<ParcelOwnership>> findAll()");
        return parcelOwnershipDAO.findAll();
    }

    @Override
    @Transactional
    public ParcelOwnership findById(int parcelOwnershipid) {
        return parcelOwnershipDAO.findById(parcelOwnershipid);
    }

    @Override
    @Transactional
    public void save(ParcelOwnership parcelOwnership) {
        parcelOwnershipDAO.save(parcelOwnership);
    }

    @Override
    @Transactional
    public void deleteById(int parcelOwnershipid) {
        parcelOwnershipDAO.deleteById(parcelOwnershipid);
    }
}
