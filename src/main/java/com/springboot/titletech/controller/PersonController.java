package com.springboot.titletech.controller;

import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;
import com.springboot.titletech.repository.ParcelOwnershipRepository;
import com.springboot.titletech.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

        private PersonRepository personRepository;

        public PersonController(PersonRepository theParcelRepository) {
            personRepository = theParcelRepository;
        }

        // add mapping for "/list"

        @GetMapping("/list")
        public String listParcelOwnerships(Model theModel) {

            // get employees from db
            List<Person> thePersons = personRepository.findAll();
//
//            for (ParcelDocument p: theParcelDocuments) {
//                System.out.print("\nid: " + p.getId());
//                System.out.print("\npurchased: " + p.getDatePurchased());
//                System.out.print("\nsold: " + p.getDatePurchased());
//            }

            // add to the spring model
            theModel.addAttribute("persons", thePersons);

            return "person/list-persons";
        }
}
