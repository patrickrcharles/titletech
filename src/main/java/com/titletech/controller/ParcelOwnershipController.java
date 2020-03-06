package com.titletech.controller;

import com.titletech.entity.ParcelOwnership;
import com.titletech.dao.ParcelOwnershipRepository;
import com.titletech.entity.Person;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        public String listParcelOwnerships(Model theModel,
            @RequestParam String sortby,
            @RequestParam String order) {

                Sort.Direction sortDirection = Sort.Direction.fromString(order);

                List<ParcelOwnership> theParcelOwnerships = parcelOwnershipRepository.findAll(Sort.by(sortDirection, sortby));
                
                // add to the spring model
                theModel.addAttribute("parcelOwnerships", theParcelOwnerships);
                theModel.addAttribute("order", sortDirection);

                return "parcelownership/list-parcelownership";
        }
}
