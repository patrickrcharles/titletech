package com.springboot.titletech.controller;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.repository.ParcelRepository;
import com.springboot.titletech.service.ParcelServiceJpa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/parcel")
public class ParcelController {
    
        private ParcelRepository parcelRepository;

        public ParcelController(ParcelRepository theParcelRepository) {
            parcelRepository = theParcelRepository;
        }

        // add mapping for "/list"

        @GetMapping("/list")
        public String listEmployees(Model theModel) {

            // get employees from db
            List<Parcel> theParcels = parcelRepository.findAll();

            // add to the spring model
            theModel.addAttribute("parcels", theParcels);

            return "parcel/list-parcels";
        }
}
