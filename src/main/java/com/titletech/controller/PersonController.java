package com.titletech.controller;

import com.titletech.entity.Person;
import com.titletech.dao.PersonRepository;
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

            List<Person> thePersons = personRepository.findAll();
            // add to the spring model
            theModel.addAttribute("persons", thePersons);

            return "person/list-persons";
        }
}
