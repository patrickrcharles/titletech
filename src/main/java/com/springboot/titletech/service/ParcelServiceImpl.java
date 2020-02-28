package com.springboot.titletech.service;

import com.springboot.titletech.dao.ParcelDAO;
import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParcelServiceImpl implements ParcelService {

    private ParcelDAO parcelDAO;

    @Autowired
    public ParcelServiceImpl(ParcelDAO theparcelDAO) {
        parcelDAO = theparcelDAO;
    }

    @Override
    @Transactional
    public List<Parcel> findAll() {
        return parcelDAO.findAll();
    }

    @Override
    @Transactional
    public Parcel findById(int theId) {
        return parcelDAO.findById(theId);
    }

    @Override
    @Transactional
    public List<Person> findCurrentOwnerByParcelId(int theId) { return  parcelDAO.findCurrentOwnerByParcelId(theId); }

    @Override
    @Transactional
    public List<Person> findPreviousOwnerByParcelId(int theId) { return  parcelDAO.findPreviousOwnerByParcelId(theId); }

    @Override
    @Transactional
    public void save(Parcel theParcel) {
        parcelDAO.save(theParcel);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        parcelDAO.deleteById(theId);
    }
}
