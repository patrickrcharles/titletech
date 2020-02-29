package com.springboot.titletech.service;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParcelJpaService {

    @Autowired
    private ParcelRepository parcelRepository;

    @GetMapping("/parceljpa")
    public List<Parcel> retrieveAllParcels() {
        System.out.print("\n*********** id::: " + parcelRepository.findAll().get(0).getId());
        System.out.print("\n*********** street ::: " + parcelRepository.findAll().get(0).getStreet());
        System.out.print("\n*********** city ::: " + parcelRepository.findAll().get(0).getCity());
        System.out.print("\n*********** state ::: " + parcelRepository.findAll().get(0).getState());
        return parcelRepository.findAll();
    }

    @GetMapping("/parceljpa/{id}")
    public Parcel retrieveParcelByID(@PathVariable Long id) {
        return parcelRepository.findById(id).get();
    }
}


