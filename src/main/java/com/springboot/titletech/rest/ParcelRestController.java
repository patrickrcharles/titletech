//package com.springboot.titletech.rest;
//
//import com.springboot.titletech.entity.Parcel;
//import com.springboot.titletech.entity.ParcelOwnership;
//import com.springboot.titletech.entity.Person;
//import com.springboot.titletech.service.ParcelService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RequestMapping("/api")
//public class ParcelRestController {
//
//	private ParcelService parcelService;
//
//	@Autowired
//	public ParcelRestController(ParcelService theParcelService) {
//		parcelService = theParcelService;
//	}
//
//	// expose "/persons" and return list of persons
//	@GetMapping("/parcel")
//	public List<Parcel> findAll() {
//		return parcelService.findAll();
//	}
//
//	// add mapping for GET /persons/{personId}
//
//	@GetMapping("/parcel/{parcelid}")
//	public Parcel getparcel(@PathVariable int parcelid) {
//
//		Parcel theParcel = parcelService.findById(parcelid);
//
//		if (theParcel == null) {
//			throw new RuntimeException("parcel id not found - " + parcelid);
//		}
//
//		return theParcel;
//	}
//
//	@GetMapping("/parcel/currentowner/{parcelid}")
//	public List<Person> getParcelCurrentOwner(@PathVariable int parcelid) {
//
//		List<Person> persons = parcelService.findCurrentOwnerByParcelId(parcelid);
//
//		if (persons == null) {
//			throw new RuntimeException("parcel id not found - " + parcelid);
//		}
//
//		return persons;
//	}
//
//	@GetMapping("/parcel/previousowner/{parcelid}")
//	public List<Person> getParcelPreviousOwner(@PathVariable int parcelid) {
//
//		List<Person> persons = parcelService.findPreviousOwnerByParcelId(parcelid);
//
//		if (persons == null) {
//			throw new RuntimeException("parcel id not found - " + parcelid);
//		}
//
//		return persons;
//	}
//
//	@GetMapping("/parcel/parcelhistory/{parcelid}")
//	public List<ParcelOwnership> getParcelHistory(@PathVariable int parcelid) {
//
//		List<ParcelOwnership> parcelOwnership = parcelService.findParcelHistory(parcelid);
//
//		if (parcelOwnership == null) {
//			throw new RuntimeException("parcel id not found - " + parcelid);
//		}
//
//		return parcelOwnership;
//	}
//
//	// add mapping for POST /persons - add new person
//
//	@PostMapping("/parcel")
//	public Parcel addparcel(@RequestBody Parcel theParcel) {
//
//		// also just in case they pass an id in JSON ... set id to 0
//		// this is to force a save of new item ... instead of update
//
//		theParcel.setId(0);
//
//		parcelService.save(theParcel);
//
//		return theParcel;
//	}
//
//	// add mapping for PUT /persons - update existing person
//
//	@PutMapping("/parcel")
//	public Parcel updateparcel(@RequestBody Parcel theParcel) {
//
//		parcelService.save(theParcel);
//
//		return theParcel;
//	}
//
//	// add mapping for DELETE /persons/{personId} - delete person
//
//	@DeleteMapping("/parcel/{parcelid}")
//	public String deleteparcel(@PathVariable int parcelid) {
//
//		Parcel tempParcel = parcelService.findById(parcelid);
//
//		// throw exception if null
//
//		if (tempParcel == null) {
//			throw new RuntimeException("parcel id not found - " + parcelid);
//		}
//
//		parcelService.deleteById(parcelid);
//
//		return "Deleted parcel id - " + parcelid;
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
//
