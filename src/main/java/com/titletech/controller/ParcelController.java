package com.titletech.controller;

import com.titletech.entity.Parcel;
import com.titletech.dao.ParcelRepository;
import com.titletech.service.ParcelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/parcel")
public class ParcelController {
    
        private ParcelRepository parcelRepository;

        public ParcelController(ParcelRepository theParcelRepository) {
            parcelRepository = theParcelRepository;
        }

        @GetMapping("/list")
        public String listParcels(Model theModel,
                                    @RequestParam String sortBy){

            // get employees from db
            List<Parcel> theParcels = parcelRepository.findAll(Sort.by( sortBy));


            // add to the spring model
            theModel.addAttribute("parcels", theParcels);

            return "parcel/list-parcels";
        }
}
