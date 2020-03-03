//package com.titletech.util;
//
//import com.titletech.entity.Parcel;
//import com.titletech.entity.ParcelDocument;
//import com.titletech.entity.ParcelOwnership;
//import com.titletech.entity.Person;
//
//import java.lang.reflect.Array;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Random;
//
//public class GenerateTestData {
//
//    protected static String SOURCES_ALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
//    protected static String SOURCES_NUMBERS = "1234567890";
//    protected static String SOURCES_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//
//    static int numToGenerate = 5;
//
//    // parcel data
//    static ArrayList<Parcel> parcelList = new ArrayList<>();
//    //parcel document data
//    static ArrayList<ParcelDocument> parcelDocumentList = new ArrayList<>();
//    // person data
//    static ArrayList<Person> personList = new ArrayList<>();
//    //parcel ownership data
//    static ArrayList<ParcelOwnership> parcelOwnershipList = new ArrayList<>();
//
//    public static void generateParcelOwnershipList() {
//
//        for(int index = 0; index < numToGenerate; index++) {
//
//            ParcelOwnership parcelOwnership = new ParcelOwnership();
//            for (ParcelDocument parcelDocument : parcelDocumentList) {
//
//                parcelOwnership.setId(index + 1);
//                parcelOwnership.setParcelid(parcelDocumentList.get(index).getParcelid());
//                parcelOwnership.setPrevious_ownerid(parcelDocumentList.get(index).getPrevious_ownerid());
//                parcelOwnership.setCurrent_ownerid(parcelDocumentList.get(index).getCurrent_ownerid());
//                parcelOwnership.setParcelDocumentid(parcelDocumentList.get(index).getId());
//                parcelOwnership.setDatePurchased(parcelDocumentList.get(index).getDatePurchased());
//                parcelOwnership.setDateSold(parcelDocumentList.get(index).getDateSold());
//            }
//            parcelOwnershipList.add(parcelOwnership);
//        }
//    }
//
//    public static void GenerateParcelList() {
//
//        for (int index = 0; index < numToGenerate; index++) {
//
//            String street = generateString(new Random(), SOURCES_NUMBERS, 5) + " " +
//                    generateString(new Random(), SOURCES_LETTERS, 7);
//            String city = generateString(new Random(), SOURCES_LETTERS, 10);
//            String state = generateString(new Random(), SOURCES_LETTERS, 10);
//            String zipCode = generateString(new Random(), SOURCES_NUMBERS, 5);
//
//            Parcel parcel = new Parcel();
//            parcel.setId(index + 1);
//            parcel.setStreet(street);
//            parcel.setCity(city);
//            parcel.setState(state);
//            parcel.setZipCode(zipCode);
//            parcel.setCurrent_ownerid(index + 1); //ex index = 1
//            parcel.setPrevious_ownerid(index); // ex, index = 0
//
//            parcelList.add(parcel);
//        }
//    }
//
//    public static void UpdatePersonList() {
//        // genrate parcelownership data
//        generateParcelOwnershipList();
//
//        // update each person with new info (parcelid, purchased/sold)
//        for (Person person : personList) {
//            // where person.id = parcelOwnership.current_ownerid
//            for (ParcelOwnership p : parcelOwnershipList) {
//                if (p.getCurrent_ownerid() == person.getId()) {
//                    person.setParcelid(p.getParcelid());
//                    person.setDatePurchased(p.getDatePurchased());
//                    person.setDateSold(p.getDateSold());
//                }
//            }
//        }
//    }
//
//    private static int createRandomIntBetween(int start, int end) {
//        return start + (int) Math.round(Math.random() * (end - start));
//    }
//
//    private static String createRandomDate(int startYear, int endYear) {
//        int day = createRandomIntBetween(1, 28);
//        int month = createRandomIntBetween(1, 12);
//        int year = createRandomIntBetween(startYear, endYear);
//        return LocalDate.of(year, month, day).toString();
//    }
//
//    private static String generateString(Random random, String characters, int length) {
//        char[] text = new char[length];
//        for (int i = 0; i < length; i++) {
//            text[i] = characters.charAt(random.nextInt(characters.length()));
//        }
//        return new String(text);
//    }
//
//    public static void GeneratePersonList() {
//
//        for (int index = 0; index < numToGenerate; index++) {
//
//            String firstName = generateString(new Random(), SOURCES_ALL, 10);
//            String middleName = generateString(new Random(), SOURCES_ALL, 10);
//            String lastName = generateString(new Random(), SOURCES_ALL, 10);
//
//            Person person = new Person();
//
//            person.setId(index + 1);
//            person.setFirstName(firstName);
//            person.setMiddleName(middleName);
//            person.setLastName(lastName);
//
//            personList.add(person);
//        }
//    }
//
//    public static void GenerateParcelDocumentList() throws ParseException {
//
//        // generate person list of names
//        GeneratePersonList();
//        // generate parcel addresses
//        GenerateParcelList();
//
//        for (int index = 0; index < numToGenerate; index++) {
//            Date purchased = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));
//            Date sold = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));
//
//            if (purchased.after(sold)) {
//                //copy original values
//                Date originalDatePurchased = purchased;
//                //swap values
//                purchased = sold;
//                sold = originalDatePurchased;
//            }
//            //get current/previous owner names
//            int currentOwnerId = 0;
//            int previousOwnerId = 0;
//            String currentOwnerName = "";
//            String previousOwnerName = "";
//
//            // find current id in list first to get correct id
//            for (Person p : personList) {
//                if (p.getId() == parcelList.get(index).getCurrent_ownerid()) {
//                    currentOwnerId = parcelList.get(index).getCurrent_ownerid();
//                    previousOwnerId = parcelList.get(index).getPrevious_ownerid();
//                }
//            }
//            for (Person p : personList) {
//                if (currentOwnerId == p.getId()) {
//                    currentOwnerName = p.getFirstName() + " " +
//                            p.getMiddleName() + " " +
//                            p.getLastName();
//                }
//                if (previousOwnerId == p.getId())
//                    previousOwnerName = p.getFirstName() + " " +
//                            p.getMiddleName() + " " +
//                            p.getLastName();
//            }
//
//            ParcelDocument parcelDocument = new ParcelDocument();
//            parcelDocument.setId(index + 1);
//            parcelDocument.setParcelid(index + 1);
//            parcelDocument.setDatePurchased(purchased.toString());
//            parcelDocument.setDateSold(sold.toString());
//            parcelDocument.setCurrent_ownerid(currentOwnerId);
//            parcelDocument.setPrevious_ownerid(previousOwnerId);
//            parcelDocument.setCurrent_owner(currentOwnerName);
//            parcelDocument.setPrevious_owner(previousOwnerName);
//
//            parcelDocumentList.add(parcelDocument);
//        }
//    }
//
//    public static void createTestData() throws ParseException {
//        //calls generatePersonList() and GenerateParcelList()
//        GenerateParcelDocumentList();
//        // calls generateParcelOwnershipList()
//        UpdatePersonList();
//    }
//
//
//    public static ArrayList<Parcel> getParcelList() {
//        return parcelList;
//    }
//
//    public static ArrayList<ParcelDocument> getParcelDocumentsList() {
//        return parcelDocumentList;
//    }
//
//    public static ArrayList<Person> getPersonList() {
//        return personList;
//    }
//
//    public static ArrayList<ParcelOwnership> getParcelOwnershipList() {
//        return parcelOwnershipList;
//    }
//}
