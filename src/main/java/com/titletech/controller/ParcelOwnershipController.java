package com.titletech.controller;

import com.titletech.entity.ParcelOwnership;
import com.titletech.dao.ParcelOwnershipRepository;
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

            List<ParcelOwnership> theParcelOwnerships = parcelOwnershipRepository.findAll();
            // add to the spring model
            theModel.addAttribute("parcelOwnerships", theParcelOwnerships);

            return "parcelownership/list-parcelownership";
        }
}
