package com.titletech.service;

import com.titletech.entity.Parcel;
import com.titletech.entity.ParcelOwnership;
import com.titletech.entity.Person;
import com.titletech.dao.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    @GetMapping("/parcel")
    public List<Parcel> retrieveAllParcels() {
        return parcelRepository.findAll();
    }

    @GetMapping("/parcel/parcel/{id}")
    public Parcel retrieveParcelByID(@PathVariable Long id) {
        return parcelRepository.findById(id).get();
    }

    @GetMapping(("/parcel/currentowner/{parcelid}"))
    public List<Person> findCurrentOwnerByParcelId(@PathVariable int parcelid) {
        return parcelRepository.findCurrentOwnerByParcelId(parcelid);
    }

    @GetMapping(("/parcel/previousowner/{parcelid}"))
    public List<Person> findPreviousOwnerByParcelId(@PathVariable int parcelid) {
        return parcelRepository.findCurrentOwnerByParcelId(parcelid);
    }

    @GetMapping(("parcel/history/{parcelid}"))
    List<ParcelOwnership> findParcelHistory(@PathVariable int parcelid) {
        return parcelRepository.findParcelHistory(parcelid);
    }
}
