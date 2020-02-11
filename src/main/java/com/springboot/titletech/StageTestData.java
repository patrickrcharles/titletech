package com.springboot.titletech;

import com.springboot.titletech.dao.PersonDAO;
import com.springboot.titletech.dao.PersonDAOImpl;
import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StageTestData {

    // database connect info
    protected static String url = "jdbc:mysql://localhost/title-ownership?useSSL=false";
    protected static String user = "root";
    protected static String password = "admin";

//    protected static String insertPersonDataQuery =
//            "INSERT INTO person (first_name, middle_name, last_name, date_purchased, date_sold, parcelid) values (?, ?, ?, ?,?,?)";
    protected static String insertPersonDataQuery =
            "INSERT INTO person (first_name, middle_name, last_name) values (?, ?, ?)";

    protected static String insertParcelDataQuery = "INSERT INTO parcel (street, city, state, zip_code,previous_ownerid, current_ownerid) values (?, ?, ?,?,?,?)";
    protected static String insertTimestampDataQuery = "INSERT INTO person (date_purchased, date_sold) values (?, ?)";
    protected static String SOURCES_ALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    protected static String SOURCES_NUMBERS = "1234567890";
    protected static String SOURCES_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    protected static String insertParceDocumentlDataQuery =
            "INSERT INTO parcel_document (parcelid, date_purchased, date_sold, current_ownerid, previous_ownerid) values (?, ?, ?,?,?)";


    public static void main(String[] args) throws java.text.ParseException {

        // get json file paths from resource folder. this is good enough for now
        // todo: write a class to load properties file

        //get paths to json test data files
//        ClassLoader classLoader = StageTestData.class.getClassLoader();
//        String jsonNamesPath = classLoader.getResource("names.json").getPath();
//        String jsonAddressesPath = classLoader.getResource("addresses.json").getPath();
//        String jsonBoughtSoldTimestampsPath = classLoader.getResource("timestamps.json").getPath();

        // get jsonArrays of test data for parsing/insertion to database
//        JSONArray personList = getJsonArrayFromFile(jsonNamesPath);
//        JSONArray parcelList = getJsonArrayFromFile(jsonAddressesPath);
//        JSONArray boughtSoldTimestampsList = getJsonArrayFromFile(jsonBoughtSoldTimestampsPath);

        // timestamps is going to a problem
        /*
        first need to create an array that makes sure data is organized by lowest date first
        identify parcels with multiple owners
        apply timestamps to that
         */

        // generate parcel data
        int numToGenerate = 5;
        ArrayList<Parcel> parcelList = new ArrayList<>();
        //generate parcel document data

        ArrayList<ParcelDocument> parcelDocumentsList = new ArrayList<>();

        for (int j = 0; j < numToGenerate; j++) {
            parcelList.add(generateParcelList(j + 1));
        }

        for (int j = 0; j < numToGenerate; j++) {
            parcelDocumentsList.add(generateParcelDocumentsList( parcelList, j));
            // generate parcel id = 1, set current owner = 2, previous owner 1

        }
        InsertParcelToDB(parcelList);
        InsertParcelDocumentToDB(parcelDocumentsList);

        // generate/insert person test data
        int numOfPersonToGenerate = 100;
        ArrayList<Person> personList = new ArrayList<>();
        for (int j = 0; j < numToGenerate; j++) {
            personList.add(generatePersonList(parcelDocumentsList, j));
        }
        InsertPersonToDB(personList);
    }

    private static ParcelDocument generateParcelDocumentsList(List<Parcel> parcelList, int i) throws java.text.ParseException {

        // needed to insert to database
        java.sql.Date sqlDatePurchased;
        java.sql.Date sqlDateSold;

        Date purchased = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));
        Date sold = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));

        if (purchased.after(sold)) {
            //copy original values
            Date originalDatePurchased = purchased;
            //Date originalDateSold = sold;
            //swap values
            purchased = sold;
            sold = originalDatePurchased;
        }

        ParcelDocument parcelDocument = new ParcelDocument();
        parcelDocument.setParcelid(i+1);
        parcelDocument.setDatePurchased(purchased.toString());
        parcelDocument.setDateSold(sold.toString());
        parcelDocument.setCurrent_ownerid(parcelList.get(i).getCurrent_ownerid());
        parcelDocument.setPrevious_ownerid(parcelList.get(i).getPrevious_ownerid());

        return parcelDocument;
    }

    private static Parcel generateParcelList(int index) {

        String street = generateString(new Random(), SOURCES_NUMBERS, 5) + " " +
                generateString(new Random(), SOURCES_LETTERS, 7);
        String city = generateString(new Random(), SOURCES_LETTERS, 10);
        String state = generateString(new Random(), SOURCES_LETTERS, 10);
        String zipCode = generateString(new Random(), SOURCES_NUMBERS, 5);

        Parcel parcel = new Parcel();
        parcel.setId(index+1);
        parcel.setStreet(street);
        parcel.setCity(city);
        parcel.setState(state);
        parcel.setZipCode(zipCode);
        parcel.setCurrent_ownerid(index+1); //ex index = 1
        parcel.setPrevious_ownerid(index); // ex, index = 0

        return parcel;
    }

    private static Person generatePersonList(List<ParcelDocument> parcelDocumentList, int index) throws java.text.ParseException {

        String firstName = generateString(new Random(), SOURCES_ALL, 10);
        String middleName = generateString(new Random(), SOURCES_ALL, 10);
        String lastName = generateString(new Random(), SOURCES_ALL, 10);

        Person person = new Person();
        person.setId(index+1);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
//        for(ParcelDocument p : parcelDocumentList){
//            if(p.getCurrent_ownerid() == person.getId()){
//                person.setParcelid(p.getParcelid());
//            }
//        }
//        person.setDatePurchased(parcelDocumentList.get(index).getDatePurchased());
//        person.setDateSold(parcelDocumentList.get(index).getDateSold());

        return person;
    }

    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static String createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day).toString();
    }

    public static String generateString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }


    private static void InsertParcelDocumentToDB(List<ParcelDocument> parcelDocumentList) {

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            for (ParcelDocument p : parcelDocumentList
            ) {
                PreparedStatement statement = conn.prepareStatement(insertParceDocumentlDataQuery);
                statement.setInt(1, p.getParcelid());
                statement.setString(2, p.getDatePurchased());
                statement.setString(3, p.getDateSold());
                statement.setInt(4, p.getCurrent_ownerid());
                statement.setInt(5, p.getPrevious_ownerid());

                int row = statement.executeUpdate();
                if (row > 0) {
                    System.out.println("Parcel document data entered successfully");
                }
                statement.close();
            }
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void InsertParcelToDB(ArrayList<Parcel> parcelList) {

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            for (Parcel p : parcelList
            ) {
                PreparedStatement statement = conn.prepareStatement(insertParcelDataQuery);
                statement.setString(1, p.getStreet());
                statement.setString(2, p.getCity());
                statement.setString(3, p.getState());
                statement.setString(4, p.getZipCode());
                statement.setInt(5, p.getPrevious_ownerid());
                statement.setInt(6, p.getCurrent_ownerid());

                int row = statement.executeUpdate();
                if (row > 0) {
                    System.out.println("Parcel data entered successfully");
                }
                statement.close();
            }
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void InsertPersonToDB(ArrayList<Person> personList) {

        int[] parcelid = getShuffledIntegerArray(personList.size());

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            for (Person p : personList
            ) {
                PreparedStatement statement = conn.prepareStatement(insertPersonDataQuery);
                statement.setString(1, p.getFirstName());
                statement.setString(2, p.getMiddleName());
                statement.setString(3, p.getLastName());
//                statement.setString(4, p.getDatePurchased());
//                statement.setString(5, p.getDateSold());
//                statement.setInt(6, parcelid[p.getId()-1]); //db index are 1-100, list 0-99

                int row = statement.executeUpdate();
                if (row > 0) {
                    System.out.println("Person data entered successfully");
                }
                statement.close();
            }
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private static Person generateAddressList(int index) throws java.text.ParseException {

        String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String firstName = generateString(new Random(), SOURCES, 10);
        String middleName = generateString(new Random(), SOURCES, 10);
        String lastName = generateString(new Random(), SOURCES, 10);

        // needed to insert to database
        java.sql.Date sqlDatePurchased;
        java.sql.Date sqlDateSold;

        Date purchased = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));
        Date sold = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));

        if (purchased.after(sold)) {
            //copy original values
            Date originalDatePurchased = purchased;
            //Date originalDateSold = sold;
            //swap values
            purchased = sold;
            sold = originalDatePurchased;
        }

        Person person = new Person();
        person.setId(index);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        person.setDatePurchased(purchased.toString());
        person.setDateSold(sold.toString());

        return person;
    }

    private static JSONArray getJsonArrayFromFile(String jsonNamesPath) {

        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonNamesPath)) {

            Object obj = parser.parse(reader);

            return (JSONArray) obj;

        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    private static int[] getShuffledIntegerArray(int size) {

        Random rd = new Random(); // creating Random object
        int[] parcelids;
        parcelids = new int[size];

        //set data equal to index
        for (int i = 0; i < parcelids.length; i++) {
            parcelids[i] = i;
        }

        // shuffle data randomly
        for (int i = 0; i < parcelids.length; i++) {
            int randomIndexToSwap = rd.nextInt(parcelids.length);
            int temp = parcelids[randomIndexToSwap];
            parcelids[randomIndexToSwap] = parcelids[i];
            parcelids[i] = temp;
        }
        // System.out.println("parcelids size : "+parcelids.length);
        return parcelids;
    }
}
