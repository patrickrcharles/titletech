package com.titletech.controller;

import com.titletech.entity.ParcelDocument;
import com.titletech.dao.ParcelDocumentRepository;
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

        @GetMapping("/list")
        public String listParcelDocuments(Model theModel) {

            List<ParcelDocument> theParcelDocuments = parcelDocumentRepository.findAll();
            // add to the spring model
            theModel.addAttribute("parceldocuments", theParcelDocuments);

            return "parceldocument/list-documents";
        }
}
