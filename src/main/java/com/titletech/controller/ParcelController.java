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
                                  @RequestParam String sortby,
                                  @RequestParam String order){

            //todo: figure out how to send sort direction as a paramter withou having to change entity

            System.out.println("paramter.direction :: " + order.toString());
            Sort.Direction sortDirection = Sort.Direction.fromString(order);

            List<Parcel> theParcels = parcelRepository.findAll(Sort.by(sortDirection, sortby));

            // add to the spring model
            theModel.addAttribute("parcels", theParcels);
            theModel.addAttribute("order", sortDirection);

            return "parcel/list-parcels";
        }
}
