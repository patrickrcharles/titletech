package com.springboot.titletech;

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
import java.util.Random;

public class StageTestData {

    // database connect info
    protected static String url = "jdbc:mysql://localhost/title-ownership?useSSL=false";
    protected static String user = "root";
    protected static String password = "admin";

    protected static String insertPersonDataQuery =
            "INSERT INTO person (first_name, middle_name, last_name, date_purchased, date_sold, parcelid) values (?, ?, ?, ?,?,?)";
    protected static String insertParcelDataQuery = "INSERT INTO parcel (street, city, state, zip_code) values (?, ?, ?,?)";
    protected static String insertTimestampDataQuery = "INSERT INTO person (date_purchased, date_sold) values (?, ?)";

    public static void main(String[] args) throws java.text.ParseException {

        // get json file paths from resource folder. this is good enough for now
        // todo: write a class to load properties file

        //get paths to json test data files
        ClassLoader classLoader = StageTestData.class.getClassLoader();
        String jsonNamesPath = classLoader.getResource("names.json").getPath();
        String jsonAddressesPath = classLoader.getResource("addresses.json").getPath();
        String jsonBoughtSoldTimestampsPath = classLoader.getResource("timestamps.json").getPath();

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

        // generate test data
        int numOfPersonToGenerate = 100;
        ArrayList<Person> personList = new ArrayList<>();
        for (int j = 0; j < numOfPersonToGenerate; j++) {
            personList.add(generatePersonList(j+1));
        }

        InsertPersonToDB(personList);




        // insert person data into array
        // personIndex is for keeping track of the index of the person object in the jsonArray
//        final int[] personIndex = new int[2000];
//        assert personList != null;
//        personList.forEach((Object person) -> {
//            InsertPersonObjectToDB((JSONObject) person, personIndex[0]);
//            // increment for next person.
//            personIndex[0]++;
//        });

        // timestamps is going to a problem
        /*
        first need to create an array that makes sure data is organized by lowest date first
        identify parcels with multiple owners
        apply timestamps to that
         */
//        assert boughtSoldTimestampsList != null;
//        boughtSoldTimestampsList.forEach((Object timestamp) -> {
//            try {
//                InsertBoughtSoldTimeStampsObjectToDB((JSONObject) timestamp);
//            } catch (java.text.ParseException e) {
//                e.printStackTrace();
//            }
//        });

//        // insert Parcel data into database
//        parcelList.forEach(parcel -> {
//            InsertParcelObjectToDB((JSONObject) parcel);
//        });

        // insert parcel dates into person based on parcelid
        // where person.parcelid = parcel.id
        //      insert person.name = parcel.currentOwner.
    }

    private static Person generatePersonList(int index) throws java.text.ParseException {

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
            //System.out.print("\n XXX - datePurchased after before dateSold");
            //copy original values
            Date originalDatePurchased = purchased;
            //Date originalDateSold = sold;
            //swap values
            purchased = sold;
            sold = originalDatePurchased;
            //convert to sql Date
            sqlDatePurchased = new java.sql.Date(purchased.getTime());
            sqlDateSold = new java.sql.Date(sold.getTime());
        } else {
            sqlDatePurchased = new java.sql.Date(purchased.getTime());
            sqlDateSold = new java.sql.Date(sold.getTime());
        }

        // todo: insert data into person table
        Person person = new Person();
        person.setId(index);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        person.setDatePurchased(purchased.toString());
        person.setDateSold(sold.toString());

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
                statement.setString(4, p.getDatePurchased());
                statement.setString(5, p.getDateSold());
                statement.setInt(6, parcelid[p.getId()-1]); //db index are 1-100, list 0-99

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

    private static void InsertPersonObjectToDB(JSONObject person, int index) {

        String firstName = (String) person.get("first_name");
        String lastName = (String) person.get("last_name");
        String middleName = (String) person.get("middle_name");

        int[] parcelid = getShuffledIntegerArray(2000);

        if (index >= 2000) {
            index = index - 2000;
            //another hack. 5000 persons, 2000 addresses.
            // reset index manually so we can have duplicate addresses
        }

        // todo: this is a hack. for some reason data inserts correctly but index still hits 2000 and throws
        // out of bounds error. figure it out later

        if (index < 2000) {
            try {

                Connection conn = DriverManager.getConnection(url, user, password);

                PreparedStatement statement = conn.prepareStatement(insertPersonDataQuery);
                statement.setString(1, firstName);
                statement.setString(2, middleName);
                statement.setString(3, lastName);
                statement.setInt(4, parcelid[index]);

                int row = statement.executeUpdate();
                if (row > 0) {
                    System.out.println("Person data entered successfully");
                }
                statement.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void InsertParcelObjectToDB(JSONObject address) {
        //Get employee object within list
        //JSONObject employeeObject = (JSONObject) employee.get("first_name");

        String city = (String) address.get("city");
        String street = (String) address.get("street");
        String state = (String) address.get("state");
        String zipCode = (String) address.get("zip_code");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = conn.prepareStatement(insertParcelDataQuery);
            statement.setString(1, street);
            statement.setString(2, city);
            statement.setString(3, state);
            statement.setString(4, zipCode);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("parcel data entered successfully");
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void InsertBoughtSoldTimeStampsObjectToDB(JSONObject object) throws java.text.ParseException {

        String datePurchased = (String) object.get("date_purchased");
        String dateSold = (String) object.get("date_sold");

        // needed to insert to database
        java.sql.Date sqlDatePurchased;
        java.sql.Date sqlDateSold;

        //String linebreak = null;

        Date purchased = new SimpleDateFormat("dd/MM/yyyy").parse(datePurchased);
        Date sold = new SimpleDateFormat("dd/MM/yyyy").parse(dateSold);

        if (purchased.after(sold)) {
            //System.out.print("\n XXX - datePurchased after before dateSold");
            //copy original values
            Date originalDatePurchased = purchased;
            //Date originalDateSold = sold;
            //swap values
            purchased = sold;
            sold = originalDatePurchased;

            //convert to sql Date
            sqlDatePurchased = new java.sql.Date(purchased.getTime());
            sqlDateSold = new java.sql.Date(sold.getTime());

        } else {
            sqlDatePurchased = new java.sql.Date(purchased.getTime());
            sqlDateSold = new java.sql.Date(sold.getTime());
        }
        // next, insert into parcel table into database
        // then, use parcelids to insert into personid
        // test with maybe 10-20 first
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = conn.prepareStatement(insertTimestampDataQuery);
            statement.setDate(1, sqlDatePurchased);
            statement.setDate(2, sqlDateSold);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("timestamp data entered successfully");
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
