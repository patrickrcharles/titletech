package com.springboot.titletech.service;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;
import com.springboot.titletech.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParcelServiceJpa {

    @Autowired
    private ParcelRepository parcelRepository;

    @RequestMapping("/parceljpa")
    public List<Parcel> retrieveAllParcels() {
        System.out.print("\n*********** id::: " + parcelRepository.findAll().get(0).getId());
        System.out.print("\n*********** street ::: " + parcelRepository.findAll().get(0).getStreet());
        System.out.print("\n*********** city ::: " + parcelRepository.findAll().get(0).getCity());
        System.out.print("\n*********** state ::: " + parcelRepository.findAll().get(0).getState());
        return parcelRepository.findAll();
    }

    @RequestMapping("/parceljpa/parceljpa/parcel/{id}")
    public Parcel retrieveParcelByID(@PathVariable Long id) {
        return parcelRepository.findById(id).get();
    }

    @RequestMapping(("/parceljpa/parcel/currentowner/{parcelid}"))
    public List<Person> findCurrentOwnerByParcelId(@PathVariable int parcelid) {
        return parcelRepository.findCurrentOwnerByParcelId(parcelid);
    }

    @RequestMapping(("/parceljpa/parcel/previousowner/{parcelid}"))
    public List<Person> findPreviousOwnerByParcelId(@PathVariable int parcelid) {
        return parcelRepository.findCurrentOwnerByParcelId(parcelid);
    }

    @RequestMapping(("/parceljpa/parcel/history/{parcelid}"))
    List<ParcelOwnership> findParcelHistory(@PathVariable int parcelid) {
        return parcelRepository.findParcelHistory(parcelid);
    }
}
