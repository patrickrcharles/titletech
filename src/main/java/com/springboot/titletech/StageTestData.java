package com.springboot.titletech;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.Person;
import org.json.simple.JSONArray;
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
    protected static String SOURCES_ALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    protected static String SOURCES_NUMBERS = "1234567890";
    protected static String SOURCES_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    protected static String insertParceDocumentlDataQuery =
            "INSERT INTO parcel_document (parcelid, date_purchased, date_sold, current_ownerid, previous_ownerid) values (?, ?, ?,?,?)";


    public static void main(String[] args) throws java.text.ParseException {


        int numToGenerate = 5;
        ArrayList<Parcel> parcelList = new ArrayList<>();
        //generate parcel data
        ArrayList<ParcelDocument> parcelDocumentsList = new ArrayList<>();
        for (int j = 0; j < numToGenerate; j++) {
            parcelList.add(generateParcelList(j + 1));
        }

        //generate parcel document data
        for (int j = 0; j < numToGenerate; j++) {
            parcelDocumentsList.add(generateParcelDocumentsList( parcelList, j));
        }

        // generate person test data
        ArrayList<Person> personList = new ArrayList<>();
        for (int j = 0; j < numToGenerate; j++) {
            personList.add(generatePersonList(j));
        }

        InsertParcelToDB(parcelList);
        InsertParcelDocumentToDB(parcelDocumentsList);
        InsertPersonToDB(personList);
    }

    private static ParcelDocument generateParcelDocumentsList(List<Parcel> parcelList, int i) throws java.text.ParseException {

        Date purchased = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));
        Date sold = new SimpleDateFormat("yyyy-dd-MM").parse(createRandomDate(1980, 2020));

        if (purchased.after(sold)) {
            //copy original values
            Date originalDatePurchased = purchased;
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

    private static Person generatePersonList(int index) {

        String firstName = generateString(new Random(), SOURCES_ALL, 10);
        String middleName = generateString(new Random(), SOURCES_ALL, 10);
        String lastName = generateString(new Random(), SOURCES_ALL, 10);

        Person person = new Person();
        person.setId(index+1);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);

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

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            for (Person p : personList
            ) {
                PreparedStatement statement = conn.prepareStatement(insertPersonDataQuery);
                statement.setString(1, p.getFirstName());
                statement.setString(2, p.getMiddleName());
                statement.setString(3, p.getLastName());

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
