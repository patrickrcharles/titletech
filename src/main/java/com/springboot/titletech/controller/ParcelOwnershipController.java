package com.springboot.titletech.controller;

import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.repository.ParcelDocumentRepository;
import com.springboot.titletech.repository.ParcelOwnershipRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/parcelownership")
public class ParcelOwnershipController {

        private ParcelOwnershipRepository parcelOwnershipRepository;

        public ParcelOwnershipController(ParcelOwnershipRepository theParcelRepository) {
            parcelOwnershipRepository = theParcelRepository;
        }

        // add mapping for "/list"

        @GetMapping("/list")
        public String listParcelOwnerships(Model theModel) {

            // get employees from db
            List<ParcelOwnership> theParcelOwnerships = parcelOwnershipRepository.findAll();
//
//            for (ParcelDocument p: theParcelDocuments) {
//                System.out.print("\nid: " + p.getId());
//                System.out.print("\npurchased: " + p.getDatePurchased());
//                System.out.print("\nsold: " + p.getDatePurchased());
//            }

            // add to the spring model
            theModel.addAttribute("parcelOwnerships", theParcelOwnerships);

            return "parcelownership/list-parcelownership";
        }
}
