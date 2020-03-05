package com.titletech.controller;

import com.titletech.entity.Parcel;
import com.titletech.entity.Person;
import com.titletech.dao.PersonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository theParcelRepository) {
        personRepository = theParcelRepository;
    }

    @GetMapping("/list")
    public String listPersons(Model theModel,
                              @RequestParam String sortby,
                              @RequestParam String order) {

        Sort.Direction sortDirection = Sort.Direction.fromString(order);

        List<Person> thePersons = personRepository.findAll(Sort.by(sortDirection, sortby));

        // add to the spring model
        theModel.addAttribute("persons", thePersons);
        theModel.addAttribute("order", sortDirection);

        return "person/list-persons";
    }
}
