package com.titletech.util;

import com.titletech.entity.Parcel;
import com.titletech.entity.ParcelDocument;
import com.titletech.entity.ParcelOwnership;
import com.titletech.entity.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GenerateTestData3 {

    protected static String SOURCES_ALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    protected static String SOURCES_NUMBERS = "1234567890";
    protected static String SOURCES_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /*
    numPerson should == numOwnership
    if numPerson =  numOwnership= 100,
    if numparcels = 10
    this will create a history of 10 owners for each parcel/address
     */
    static int numParcelOwnershipToGenerate;
    static int numPersonToGenerate = numParcelOwnershipToGenerate = 600;

    static int numOfOwners = 30;
    static int numParcelsToGenerate = numParcelOwnershipToGenerate/numOfOwners;

    // parcel data
    static ArrayList<Parcel> parcelList = new ArrayList<>(numParcelsToGenerate);
    //parcel document data
    static ArrayList<ParcelDocument> parcelDocumentList = new ArrayList<>(numPersonToGenerate);
    // person data
    static ArrayList<Person> personList = new ArrayList<>(numPersonToGenerate);
    //parcel ownership data
    static ArrayList<ParcelOwnership> parcelOwnershipList = new ArrayList<>(numParcelOwnershipToGenerate);

    public static void createTestData() throws ParseException {

        //generate list of parcel addresses
        //generateParcelList();
        generateParcelListFromJsonFile();
        //generate person list of names
        generatePersonListFromJsonFile();

        int ownershipHistoryInstance = 0;
        int numOwnerHistoryToGenerate = numParcelsToGenerate;

        for (int i = 0; i < numOwnerHistoryToGenerate; i++) {
            numOwnerHistoryToGenerate += ownershipHistoryInstance;
            int parcelid = numOwnerHistoryToGenerate - i;

            // ex. when i = 0, start = 0.
            //    when i = 1, start = 10, this works
            int startTransactionId = (numOwnerHistoryToGenerate * i) + 1;
            GenerateOwnerShipHistory(numParcelsToGenerate, startTransactionId, parcelid);
        }

        generateParcelDocumentInfo();
        updateParcelListOwnersIds();
        updatePersonSoldPurchasedDates();
    }

    //generate a parcel ownership history for the specified parcel
    public static void GenerateOwnerShipHistory(int numOfTransactions, int startTransactionId, int parcelid) throws ParseException {

        int ownerShipInstanceNumber = 0;

        //first date
        Date purchased = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1960, 1965));
        // sold date, add 5 years
        Date sold = addToDate(purchased, 0, 5);
        // -1+1+1 is just the way to gen 1-N numbers instead of 0-N numbers
        int currentOwnerid = new Random().nextInt((numPersonToGenerate - 1) + 1) + 1;
        int previousOwnerid = new Random().nextInt((numPersonToGenerate - 1) + 1) + 1;

        for (int i = 0; i < numOfTransactions; i++) {
            // next transaction dates, creating a chain.
            // previous sold date becomes new purchased date, new sold date add 5 more years
            if (ownerShipInstanceNumber > 0) {
                purchased = sold;
                sold = addToDate(purchased, createRandomIntBetween(1, 12), createRandomIntBetween(1, 5));
                currentOwnerid = new Random().nextInt((numPersonToGenerate - 1) + 1) + 1;
                //previous owner is the currentowner of previous list entry
                // get last object
                previousOwnerid = parcelOwnershipList.get(parcelOwnershipList.size() - 1).getCurrentOwnerid();
            }

            // generate parcel ownership
            ParcelOwnership parcelOwnership = new ParcelOwnership();
            parcelOwnership.setId(startTransactionId + i);
            parcelOwnership.setParcelid(parcelid);
            parcelOwnership.setParcelDocumentid(startTransactionId + i);
            parcelOwnership.setDatePurchased(purchased.toString());
            parcelOwnership.setDateSold(sold.toString());
            parcelOwnership.setCurrentOwnerid(currentOwnerid);
            parcelOwnership.setPreviousOwnerid(previousOwnerid);

            parcelOwnership.setCurrentOwner(getCurrentParcelOwnerNameIdById(currentOwnerid));
            parcelOwnership.setPreviousOwner(getPreviousParcelOwnerNameById(previousOwnerid));

            parcelOwnershipList.add(parcelOwnership);

            //update current/previous owner owner
            personList.get(currentOwnerid - 1).setIsCurrentOwner(1);
            personList.get(previousOwnerid - 1).setIsCurrentOwner(0);
            ownerShipInstanceNumber++;
        }
    }

    public static void generateParcelDocumentInfo() {

        for (ParcelOwnership p : parcelOwnershipList) {

            ParcelDocument parcelDocument = new ParcelDocument();
            parcelDocument.setId(p.getParcelDocumentid());
            parcelDocument.setPreviousOwnerid(p.getPreviousOwnerid());
            parcelDocument.setParcelid(p.getParcelid());
            parcelDocument.setDatePurchased(p.getDatePurchased());
            parcelDocument.setDateSold(p.getDateSold());
            parcelDocument.setCurrentOwnerid(p.getCurrentOwnerid());
            parcelDocument.setPreviousOwnerid(p.getPreviousOwnerid());

            parcelDocument.setCurrentOwner(p.getCurrentOwner());
            parcelDocument.setPreviousOwner(p.getPreviousOwner());

            parcelDocumentList.add(parcelDocument);
        }
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

            parcelList.get(parcelIdToUpdate - 1).setCurrentOwnerid(ownership.getCurrentOwnerid());
            parcelList.get(parcelIdToUpdate - 1).setPreviousOwnerid(ownership.getPreviousOwnerid());
        }
    }

    private static String getCurrentParcelOwnerNameIdById(int personId) {
        String currentOwnerName = null;
        String firstName;
        String middleName;
        String lastName;

        for (Person p : personList) {
            if (p.getId() == personId) {
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

        for (Person p : personList) {
            if (p.getId() == personId) {
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
            if (p.getCurrentOwnerid() == index)
                currentParcelOwnerId = p.getCurrentOwnerid();
        }
        return currentParcelOwnerId;
    }

    private static int getPreviousParcelOwnerIdByParcelId(int index) {
        int previousParcelOwnerId = 0;
        for (ParcelOwnership p : parcelOwnershipList) {
            if (p.getPreviousOwnerid() == index)
                previousParcelOwnerId = p.getCurrentOwnerid();
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

    public static void generatePersonListFromJsonFile() {

        ClassLoader classLoader = GenerateTestData3.class.getClassLoader();
        String jsonNamesPath = classLoader.getResource("names.json").getPath();
        // get jsonArrays of test data for parsing/insertion to database
        JSONArray personList = getJsonArrayFromFile(jsonNamesPath);

        // insert person data into array
        // personIndex is for keeping track of the index of the person object in the jsonArray
        final int[] personIndex = {0};
        assert personList != null;
        personList.forEach((Object person) -> {
            if(personIndex[0] < numPersonToGenerate) {
                InsertJsonObjectToPersonObject((JSONObject) person, personIndex[0]);
                // increment for next person.
                personIndex[0]++;
            }
        });
    }

    public static void generateParcelListFromJsonFile() {

        ClassLoader classLoader = GenerateTestData3.class.getClassLoader();
        String jsonNamesPath = classLoader.getResource("addresses.json").getPath();
        // get jsonArrays of test data for parsing/insertion to database
        JSONArray addressList = getJsonArrayFromFile(jsonNamesPath);

        // insert person data into array
        // personIndex is for keeping track of the index of the person object in the jsonArray
        final int[] addressIndex = {0};
        assert addressList != null;
        addressList.forEach((Object address) -> {
            if(addressIndex[0] < numParcelsToGenerate) {
                InsertJsonObjectToParcelObject((JSONObject) address, addressIndex[0]);
                // increment for next person.
                addressIndex[0]++;
            }
        });


    }

    private static void InsertJsonObjectToPersonObject(JSONObject personObj, int index) {


        String firstName = (String) personObj.get("first_name");
        String lastName = (String) personObj.get("last_name");
        String middleName = (String) personObj.get("middle_name");

        Person person = new Person();

        person.setId(index + 1);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);

        personList.add(person);
    }

    private static void InsertJsonObjectToParcelObject(JSONObject parcelObj, int index) {

        Parcel parcel = new Parcel();
        String street =(String) parcelObj.get("street");
        String city = (String) parcelObj.get("city");
        String state = (String) parcelObj.get("state");
        String zipCode = (String) parcelObj.get("zip_code");

        parcel.setId(index + 1);
        parcel.setStreet(street);
        parcel.setCity(city);
        parcel.setState(state);
        parcel.setZipCode(zipCode);

        parcelList.add(parcel);
    }

    private static JSONArray getJsonArrayFromFile(String jsonNamesPath) {

        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonNamesPath)) {

            Object obj = parser.parse(reader);

            return (JSONArray) obj;

        } catch (IOException | org.json.simple.parser.ParseException exception) {
            exception.printStackTrace();
            return null;
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
                if (p.getCurrentOwnerid() == person.getId()) {
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

    private static Date addToDate(Date date, int months, int years) {


        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        c.add(Calendar.YEAR, years);
        date = c.getTime();

        return date;
    }


    private static String generateString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
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
