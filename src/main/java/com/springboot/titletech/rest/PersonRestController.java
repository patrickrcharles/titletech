//package com.springboot.titletech.rest;
//
//import java.util.List;
//
//import com.springboot.titletech.entity.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RequestMapping("/api")
//public class PersonRestController {
//
//	private PersonService personService;
//
//	@Autowired
//	public PersonRestController(PersonService thepersonService) {
//		personService = thepersonService;
//	}
//
//	// expose "/persons" and return list of persons
//	@GetMapping("/person")
//	public List<Person> findAll() {
//		return personService.findAll();
//	}
//
//	// add mapping for GET /persons/{personId}
//
//	@GetMapping("/person/{personid}")
//	public Person getperson(@PathVariable int personid) {
//
//		Person thePerson = personService.findById(personid);
//
//		if (thePerson == null) {
//			throw new RuntimeException("person id not found - " + personid);
//		}
//
//		return thePerson;
//	}
//
//	// add mapping for POST /persons - add new person
//
//	@PostMapping("/person")
//	public Person addperson(@RequestBody Person thePerson) {
//
//		// also just in case they pass an id in JSON ... set id to 0
//		// this is to force a save of new item ... instead of update
//
//		thePerson.setId(0);
//
//		personService.save(thePerson);
//
//		return thePerson;
//	}
//
//	// add mapping for PUT /persons - update existing person
//
//	@PutMapping("/person")
//	public Person updateperson(@RequestBody Person thePerson) {
//
//		personService.save(thePerson);
//
//		return thePerson;
//	}
//
//	// add mapping for DELETE /persons/{personId} - delete person
//
//	@DeleteMapping("/person/{personid}")
//	public String deleteperson(@PathVariable int personid) {
//
//		Person tempperson = personService.findById(personid);
//
//		// throw exception if null
//
//		if (tempperson == null) {
//			throw new RuntimeException("person id not found - " + personid);
//		}
//
//		personService.deleteById(personid);
//
//		return "Deleted person id - " + personid;
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
