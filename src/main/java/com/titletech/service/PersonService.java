package com.titletech.service;

import com.titletech.entity.Person;
import com.titletech.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository thepersonService) {
        personRepository = thepersonService;
    }

    // expose "/persons" and return list of persons
    @GetMapping("/person")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    // add mapping for GET /persons/{personId}

    @GetMapping("/person/{personid}")
    public Optional<Person> getperson(@PathVariable long personid) {

        Optional<Person> thePerson = personRepository.findById(personid);

        if (thePerson == null) {
            throw new RuntimeException("person id not found - " + personid);
        }

        return thePerson;
    }

    // add mapping for POST /persons - add new person

    @PostMapping("/person")
    public Person addperson(@RequestBody Person thePerson) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        thePerson.setId(0);

        personRepository.save(thePerson);

        return thePerson;
    }

    // add mapping for PUT /persons - update existing person

    @PutMapping("/person")
    public Person updateperson(@RequestBody Person thePerson) {

        personRepository.save(thePerson);

        return thePerson;
    }

    // add mapping for DELETE /persons/{personId} - delete person

    @DeleteMapping("/person/{personid}")
    public String deleteperson(@PathVariable long personid) {

        Optional<Person> tempperson = personRepository.findById(personid);

        // throw exception if null

        if (tempperson == null) {
            throw new RuntimeException("person id not found - " + personid);
        }

        personRepository.deleteById(personid);

        return "Deleted person id - " + personid;
    }
}
