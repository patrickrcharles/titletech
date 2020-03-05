package com.titletech.controller;

import com.titletech.entity.Parcel;
import com.titletech.entity.ParcelDocument;
import com.titletech.dao.ParcelDocumentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/parceldocument")
public class ParcelDocumentController {

    private ParcelDocumentRepository parcelDocumentRepository;

    public ParcelDocumentController(ParcelDocumentRepository theParcelRepository) {
        parcelDocumentRepository = theParcelRepository;
    }

    @GetMapping("/list")
    public String listParcelDocuments(Model theModel,
                                      @RequestParam String sortby,
                                      @RequestParam String order) {

        System.out.println("listParcelDocuments :: sortby" + sortby );

        Sort.Direction sortDirection = Sort.Direction.fromString(order);

        List<ParcelDocument> theParcelDocuments = parcelDocumentRepository.findAll(Sort.by(sortDirection, sortby));

        // add to the spring model
        theModel.addAttribute("parceldocuments", theParcelDocuments);
        theModel.addAttribute("order", sortDirection);

        return "parceldocument/list-documents";
    }
}
