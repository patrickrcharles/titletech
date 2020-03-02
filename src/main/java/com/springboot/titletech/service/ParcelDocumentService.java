package com.springboot.titletech.service;

import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.repository.ParcelDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ParcelDocumentService {
    private ParcelDocumentRepository parcelDocumentRepository;

    @Autowired
    public ParcelDocumentService(ParcelDocumentRepository theParcelDocumentService) {
        parcelDocumentRepository = theParcelDocumentService;
    }

    @GetMapping("/parceldocument")
    public List<ParcelDocument> findAll() {
        return parcelDocumentRepository.findAll();
    }

    @GetMapping("/parceldocument/{parcelDocumentid}")
    public Optional<ParcelDocument> getParcelDocumentById(@PathVariable Long parcelDocumentid) {

        Optional<ParcelDocument> theParcelDocument = parcelDocumentRepository.findById(parcelDocumentid);

        if (theParcelDocument == null) {
            throw new RuntimeException("ParcelDocument id not found - " + parcelDocumentid);
        }

        return theParcelDocument;
    }

    @GetMapping("/parceldocument/parcel/{parcelid}")
    public List<ParcelDocument> getParcelDocumenstByParcelId(@PathVariable int parcelid) {

        List<ParcelDocument> theParcelDocuments = parcelDocumentRepository.findParcelDocumentsByParcelID(parcelid);

        if (theParcelDocuments == null) {
            throw new RuntimeException("ParcelDocument id not found - " + parcelid);
        }

        return theParcelDocuments;
    }

    // add mapping for POST /persons - add new person

    @PostMapping("/parceldocument")
    public ParcelDocument addParcelDocument(@RequestBody ParcelDocument theParcelDocument) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theParcelDocument.setId(0);

        parcelDocumentRepository.save(theParcelDocument);

        return theParcelDocument;
    }

    // add mapping for PUT /persons - update existing person

    @PutMapping("/parceldocument")
    public ParcelDocument updateParcelDocument(@RequestBody ParcelDocument theParcelDocument) {

        parcelDocumentRepository.save(theParcelDocument);

        return theParcelDocument;
    }

    // add mapping for DELETE /persons/{personId} - delete person

    @DeleteMapping("/parceldocument/{parcelDocumentid}")
    public String deleteParcelDocument(@PathVariable Long parcelDocumentid) {

        Optional<ParcelDocument> tempParcelDocument = parcelDocumentRepository.findById(parcelDocumentid);

        // throw exception if null

        if (tempParcelDocument == null) {
            throw new RuntimeException("ParcelDocument id not found - " + parcelDocumentid);
        }

        parcelDocumentRepository.deleteById(parcelDocumentid);

        return "Deleted ParcelDocument id - " + parcelDocumentid;
    }
}
