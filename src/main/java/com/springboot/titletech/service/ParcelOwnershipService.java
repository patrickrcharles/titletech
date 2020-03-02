package com.springboot.titletech.service;

import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.repository.ParcelOwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ParcelOwnershipService {

    private ParcelOwnershipRepository parcelOwnershipRepository;

    @Autowired
    public ParcelOwnershipService(ParcelOwnershipRepository theParcelOwnershipService) {
        parcelOwnershipRepository = theParcelOwnershipService;
    }

    @GetMapping("/parcelownership")
    public List<ParcelOwnership> findAll() {
        return parcelOwnershipRepository.findAll();
    }

    @GetMapping("/parcelownership/{parcelOwnershipid}")
    public Optional<ParcelOwnership> getParcelOwnership(@PathVariable Long parcelOwnershipid) {

        Optional<ParcelOwnership> parcelOwnership = parcelOwnershipRepository.findById(parcelOwnershipid);

        if (parcelOwnership == null) {
            throw new RuntimeException("Parcel Ownership id not found - " + parcelOwnershipid);
        }

        return parcelOwnership;
    }

    // add mapping for POST /persons - add new person

    @PostMapping("/parcelownership")
    public ParcelOwnership addParcelOwnership(@RequestBody ParcelOwnership theParcelOwnership) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theParcelOwnership.setId(0);

        parcelOwnershipRepository.save(theParcelOwnership);

        return theParcelOwnership;
    }

    // add mapping for PUT /persons - update existing person

    @PutMapping("/parcelownership")
    public ParcelOwnership updateParcelOwnership(@RequestBody ParcelOwnership parcelOwnership) {

        parcelOwnershipRepository.save(parcelOwnership);

        return parcelOwnership;
    }

    // add mapping for DELETE /persons/{personId} - delete person

    @DeleteMapping("/parcelownership/{parcelOwnershipid}")
    public String deleteParcelOwnership(@PathVariable long parcelOwnershipid) {

        Optional<ParcelOwnership> tempParcelOwnership = parcelOwnershipRepository.findById(parcelOwnershipid);

        // throw exception if null

        if (tempParcelOwnership == null) {
            throw new RuntimeException("Parcel Ownership id not found - " + parcelOwnershipid);
        }

        parcelOwnershipRepository.deleteById(parcelOwnershipid);

        return "Parcel Ownership id - " + parcelOwnershipid;
    }
}
