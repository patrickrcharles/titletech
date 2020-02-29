package com.springboot.titletech.controller;

import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.repository.ParcelDocumentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/parceldocument")
public class ParcelDocumentController {

        private ParcelDocumentRepository parcelDocumentRepository;

        public ParcelDocumentController(ParcelDocumentRepository theParcelRepository) {
            parcelDocumentRepository = theParcelRepository;
        }

        // add mapping for "/list"

        @GetMapping("/list")
        public String listParcelDocuments(Model theModel) {

            // get employees from db
            List<ParcelDocument> theParcelDocuments = parcelDocumentRepository.findAll();
//
//            for (ParcelDocument p: theParcelDocuments) {
//                System.out.print("\nid: " + p.getId());
//                System.out.print("\npurchased: " + p.getDatePurchased());
//                System.out.print("\nsold: " + p.getDatePurchased());
//            }

            // add to the spring model
            theModel.addAttribute("parceldocuments", theParcelDocuments);

            return "parceldocument/list-documents";
        }
}
