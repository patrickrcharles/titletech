package com.springboot.titletech.rest;

import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.service.ParcelOwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParcelOwnershipRestController {

    private ParcelOwnershipService parcelOwnershipService;

    @Autowired
    public ParcelOwnershipRestController(ParcelOwnershipService theParcelOwnershipService) {
        parcelOwnershipService = theParcelOwnershipService;
    }

    @GetMapping("/parcelownership")
    public List<ParcelOwnership> findAll() {
        return parcelOwnershipService.findAll();
    }

    @GetMapping("/parcelownership/{parcelownershipid}")
    public ParcelOwnership getParcelOwnership(@PathVariable int parcelOwnershipid) {

        ParcelOwnership parcelOwnership = parcelOwnershipService.findById(parcelOwnershipid);

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

        parcelOwnershipService.save(theParcelOwnership);

        return theParcelOwnership;
    }

    // add mapping for PUT /persons - update existing person

    @PutMapping("/parcelownership")
    public ParcelOwnership updateParcelOwnership(@RequestBody ParcelOwnership parcelOwnership) {

        parcelOwnershipService.save(parcelOwnership);

        return parcelOwnership;
    }

    // add mapping for DELETE /persons/{personId} - delete person

    @DeleteMapping("/parcelownership/{parcelownershipid}")
    public String deleteParcelOwnership(@PathVariable int parcelOwnershipid) {

        ParcelOwnership tempParcelOwnership = parcelOwnershipService.findById(parcelOwnershipid);

        // throw exception if null

        if (tempParcelOwnership == null) {
            throw new RuntimeException("Parcel Ownership id not found - " + parcelOwnershipid);
        }

        parcelOwnershipService.deleteById(parcelOwnershipid);

        return "Parcel Ownership id - " + parcelOwnershipid;
    }
}
