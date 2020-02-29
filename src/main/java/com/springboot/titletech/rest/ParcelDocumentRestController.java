//package com.springboot.titletech.rest;
//
//import com.springboot.titletech.entity.ParcelDocument;
//import com.springboot.titletech.service.ParcelDocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RequestMapping("/api")
//public class ParcelDocumentRestController {
//
//    private ParcelDocumentService parcelDocumentService;
//
//    @Autowired
//    public ParcelDocumentRestController(ParcelDocumentService theParcelDocumentService) {
//        parcelDocumentService = theParcelDocumentService;
//    }
//
//    @GetMapping("/parceldocument")
//    public List<ParcelDocument> findAll() {
//        return parcelDocumentService.findAll();
//    }
//
//    @GetMapping("/parceldocument/{parcelDocumentid}")
//    public ParcelDocument getParcelDocument(@PathVariable int parcelDocumentid) {
//
//        ParcelDocument theParcelDocument = parcelDocumentService.findById(parcelDocumentid);
//
//        if (theParcelDocument == null) {
//            throw new RuntimeException("ParcelDocument id not found - " + parcelDocumentid);
//        }
//
//        return theParcelDocument;
//    }
//
//    // add mapping for POST /persons - add new person
//
//    @PostMapping("/parceldocument")
//    public ParcelDocument addParcelDocument(@RequestBody ParcelDocument theParcelDocument) {
//
//        // also just in case they pass an id in JSON ... set id to 0
//        // this is to force a save of new item ... instead of update
//
//        theParcelDocument.setId(0);
//
//        parcelDocumentService.save(theParcelDocument);
//
//        return theParcelDocument;
//    }
//
//    // add mapping for PUT /persons - update existing person
//
//    @PutMapping("/parceldocument")
//    public ParcelDocument updateParcelDocument(@RequestBody ParcelDocument theParcelDocument) {
//
//        parcelDocumentService.save(theParcelDocument);
//
//        return theParcelDocument;
//    }
//
//    // add mapping for DELETE /persons/{personId} - delete person
//
//    @DeleteMapping("/parceldocument/{parcelDocumentid}")
//    public String deleteParcelDocument(@PathVariable int parcelDocumentid) {
//
//        ParcelDocument tempParcelDocument = parcelDocumentService.findById(parcelDocumentid);
//
//        // throw exception if null
//
//        if (tempParcelDocument == null) {
//            throw new RuntimeException("ParcelDocument id not found - " + parcelDocumentid);
//        }
//
//        parcelDocumentService.deleteById(parcelDocumentid);
//
//        return "Deleted ParcelDocument id - " + parcelDocumentid;
//    }
//}
