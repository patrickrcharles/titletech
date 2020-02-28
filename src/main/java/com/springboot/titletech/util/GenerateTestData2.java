package com.springboot.titletech.util;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class GenerateTestData2 {

    protected static String SOURCES_ALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    protected static String SOURCES_NUMBERS = "1234567890";
    protected static String SOURCES_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static int numParcelsToGenerate = 10;
    static int numPersonToGenerate = 30;
    static int numParcelOwnershipToGenerate = 30;

    // parcel data
    static ArrayList<Parcel> parcelList = new ArrayList<>(numParcelsToGenerate);
    //parcel document data
    static ArrayList<ParcelDocument> parcelDocumentList = new ArrayList<>(numPersonToGenerate);
    // person data
    static ArrayList<Person> personList = new ArrayList<>(numPersonToGenerate);
    //parcel ownership data
    static ArrayList<ParcelOwnership> parcelOwnershipList = new ArrayList<>(numPersonToGenerate);


    public static void GenerateOwnerShipInstance(int index) throws ParseException {
        Date purchased = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));
        Date sold = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));

        if (purchased.after(sold)) {
            //copy original values
            Date originalDatePurchased = purchased;
            //swap values
            purchased = sold;
            sold = originalDatePurchased;
        }

        int parcelid = new Random().nextInt((numParcelsToGenerate - 1) + 1) + 1;
        int currentOwnerid = new Random().nextInt((numPersonToGenerate - 1) + 1) + 1;
        int previousOwnerid = new Random().nextInt((numPersonToGenerate - 1) + 1) + 1;
        int parcelDocumentid = index + 1; // should be same as parcelOwnershipId

        // generate parcel ownership
        ParcelOwnership parcelOwnership = new ParcelOwnership();
        parcelOwnership.setId(index + 1);
        parcelOwnership.setParcelid(parcelid);
        parcelOwnership.setParcelDocumentid(parcelDocumentid);
        parcelOwnership.setDatePurchased(purchased.toString());
        parcelOwnership.setDateSold(sold.toString());
        parcelOwnership.setCurrent_ownerid(currentOwnerid);
        parcelOwnership.setPrevious_ownerid(previousOwnerid);

        parcelOwnership.setCurrent_owner(getCurrentParcelOwnerNameIdById(currentOwnerid));
        parcelOwnership.setPrevious_owner(getPreviousParcelOwnerNameById(previousOwnerid));

        parcelOwnershipList.add(parcelOwnership);

    }

    public static void generateParcelDocumentInfo() {

        for (ParcelOwnership p : parcelOwnershipList) {
            ParcelDocument parcelDocument = new ParcelDocument();
            int index = p.getId() - 1;

            parcelDocument.setId(index + 1);
            parcelDocument.setPrevious_ownerid(parcelOwnershipList.get(index).getPrevious_ownerid());
            parcelDocument.setParcelid(parcelOwnershipList.get(index).getParcelid());
            parcelDocument.setDatePurchased(parcelOwnershipList.get(index).getDatePurchased());
            parcelDocument.setDateSold(parcelOwnershipList.get(index).getDateSold());
            parcelDocument.setCurrent_ownerid(parcelOwnershipList.get(index).getCurrent_ownerid());
            parcelDocument.setPrevious_ownerid(parcelOwnershipList.get(index).getPrevious_ownerid());

            parcelDocument.setCurrent_owner(parcelOwnershipList.get(index).getCurrent_owner());
            parcelDocument.setPrevious_owner(parcelOwnershipList.get(index).getPrevious_owner());

            parcelDocumentList.add(parcelDocument);
        }
        String line;
    }

    public static void generateParcelList() {

        for (int index = 0; index < numParcelsToGenerate; index++) {
            Parcel parcel = new Parcel();
            String street = generateString(new Random(), SOURCES_NUMBERS, 5) + " " +
                    generateString(new Random(), SOURCES_LETTERS, 7);
            String city = generateString(new Random(), SOURCES_LETTERS, 10);
            String state = generateString(new Random(), SOURCES_LETTERS, 2).toUpperCase();
            String zipCode = generateString(new Random(), SOURCES_NUMBERS, 5);

            parcel.setId(index + 1);
            parcel.setStreet(street);
            parcel.setCity(city);
            parcel.setState(state);
            parcel.setZipCode(zipCode);

            parcelList.add(parcel);
        }
    }

    private static void updateParcelListOwnersIds() {

        for (ParcelOwnership ownership : parcelOwnershipList) {

            int parcelIdToUpdate = ownership.getParcelid();

            parcelList.get(parcelIdToUpdate - 1).setCurrent_ownerid(ownership.getCurrent_ownerid());
            parcelList.get(parcelIdToUpdate - 1).setPrevious_ownerid(ownership.getPrevious_ownerid());
        }
    }

    private static void updateParcelDocumentNames() {

        for (int i = 0; i < parcelDocumentList.size(); i++) {
            if (parcelOwnershipList.get(i).getParcelDocumentid() == parcelDocumentList.get(i).getId()) {

                ParcelOwnership parcelOwnership = parcelOwnershipList.get(i);
                int parcelDocumentIdToUpdate = parcelOwnership.getParcelDocumentid();

                parcelDocumentList.get(parcelDocumentIdToUpdate - 1).setCurrent_owner(parcelOwnership.getCurrent_owner());
                parcelDocumentList.get(parcelDocumentIdToUpdate - 1).setPrevious_owner(parcelOwnership.getPrevious_owner());
            }
        }
    }

    private static String getCurrentParcelOwnerNameIdById(int personId) {
        String currentOwnerName = null;
        String firstName;
        String middleName;
        String lastName;

        for (Person p: personList) {
            if(p.getId() == personId){
                firstName = p.getFirstName();
                middleName = p.getMiddleName();
                lastName = p.getLastName();
                currentOwnerName = firstName + " " + middleName + " " + lastName;
            }
        }
        return currentOwnerName;
    }

    private static String getPreviousParcelOwnerNameById(int personId) {
        String previousOwnerName = null;
        String firstName;
        String middleName;
        String lastName;

        for (Person p: personList) {
            if(p.getId() == personId){
                firstName = p.getFirstName();
                middleName = p.getMiddleName();
                lastName = p.getLastName();
                previousOwnerName = firstName + " " + middleName + " " + lastName;
            }
        }
        return previousOwnerName;
    }

    private static int getCurrentParcelOwnerIdByParcelId(int index) {
        int currentParcelOwnerId = 0;
        for (ParcelOwnership p : parcelOwnershipList) {
            if (p.getCurrent_ownerid() == index)
                currentParcelOwnerId = p.getCurrent_ownerid();
        }
        return currentParcelOwnerId;
    }

    private static int getPreviousParcelOwnerIdByParcelId(int index) {
        int previousParcelOwnerId = 0;
        for (ParcelOwnership p : parcelOwnershipList) {
            if (p.getPrevious_ownerid() == index)
                previousParcelOwnerId = p.getCurrent_ownerid();
        }
        return previousParcelOwnerId;
    }

    public static void generatePersonList() {

        for (int index = 0; index < numPersonToGenerate; index++) {
            String firstName = generateString(new Random(), SOURCES_ALL, 5);
            String middleName = generateString(new Random(), SOURCES_ALL, 5);
            String lastName = generateString(new Random(), SOURCES_ALL, 7);

            Person person = new Person();

            person.setId(index + 1);
            person.setFirstName(firstName);
            person.setMiddleName(middleName);
            person.setLastName(lastName);

            personList.add(person);
        }
    }

    public static String generateFullName() {
        String firstName = generateString(new Random(), SOURCES_ALL, 5);
        String middleName = generateString(new Random(), SOURCES_ALL, 5);
        String lastName = generateString(new Random(), SOURCES_ALL, 7);

        Person person = new Person();

        //add index to person object starting at 1. initial size of list is 0
        person.setId(personList.size() + 1);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);

        personList.add(person);

        return firstName + " " + middleName + " " + lastName;
    }

    public static void updatePersonSoldPurchasedDates() {

        // update each person with new info (parcelid, purchased/sold)
        for (Person person : personList) {
            // where person.id = parcelOwnership.current_ownerid
            for (ParcelOwnership p : parcelOwnershipList) {
                if (p.getCurrent_ownerid() == person.getId()) {
                    person.setParcelid(p.getParcelid());
                    person.setDatePurchased(p.getDatePurchased());
                    person.setDateSold(p.getDateSold());
                }
            }
        }
    }

    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static String createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day).toString();
    }

    private static String generateString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static void createTestData() throws ParseException {

        //todo: determine when a property is sold and adjust dates

        //generate list of parcel addresses
        generateParcelList();
        //generate person list of names
        generatePersonList();

        for (int index = 0; index < numParcelOwnershipToGenerate; index++) {
            GenerateOwnerShipInstance(index);
        }
        generateParcelDocumentInfo();
        updateParcelListOwnersIds();
        updatePersonSoldPurchasedDates();
        updateParcelDocumentNames();

    }


    public static ArrayList<Parcel> getParcelList() {
        return parcelList;
    }

    public static ArrayList<ParcelDocument> getParcelDocumentsList() {
        return parcelDocumentList;
    }

    public static ArrayList<Person> getPersonList() {
        return personList;
    }

    public static ArrayList<ParcelOwnership> getParcelOwnershipList() {
        return parcelOwnershipList;
    }
}
