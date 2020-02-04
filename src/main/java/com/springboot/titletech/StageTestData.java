package com.springboot.titletech;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class StageTestData {

    public static void main(String[] args) throws URISyntaxException {

        // get json file paths from resource folder
        ClassLoader classLoader = StageTestData.class.getClassLoader();
        String jsonNamesPath = classLoader.getResource("names.json").getPath();
        String jsonAddressesPath = classLoader.getResource("addresses.json").getPath();
        String jsonBoughtSoldTimestampsPath = classLoader.getResource("timestamps.json").getPath();

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(jsonNamesPath)) {

            Object obj = parser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            //System.out.println(personList);

            // begin index tracker. used to access array of shuffled parcel ids
            // called in InsertPersonObjectToDB() function.
            // function is used to quasi randomize data
            final int[] personIndex = new int[2000];
            // loop through each name and insert into DB while incrementing
            // the tracking the current index of the object in jsonArray
            personList.forEach(person -> {
                InsertPersonObjectToDB((JSONObject) person, personIndex[0]);
                personIndex[0]++;
            });

        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        try (Reader reader = new FileReader(jsonAddressesPath)) {

            Object obj = parser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            //System.out.println(personList);
            //Iterate over array

            personList.forEach(person -> {
                InsertAddressObjectToDB((JSONObject) person);
            });

        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
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

    private static void InsertPersonObjectToDB(JSONObject person, int index) {
        //System.out.println("person : " + person);
        String firstName = (String) person.get("first_name");
        String lastName = (String) person.get("last_name");
        String middleName = (String) person.get("middle_name");

        int[] parcelid = getShuffledIntegerArray(2000);

        if (index >= 2000) {
            index = index - 2000;
            //another hack. 5000 persons, 2000 addresses.
            // reset index manually so we can have duplicate addresses
        }

        // this is a hack. for some reason data inserts correctly but index still hits 2000 and throws
        // out of bounds error

//            System.out.println(firstName + " " + middleName + " " + lastName);
//            System.out.println("personIndex = " + index);
//            System.out.println("parcelid[" + index + "] = " + parcelid[index]);
//
//            System.out.println("parcelid[" + index + "] = " + parcelid[index]);
//            System.out.println("parcelid.size = " + parcelid.length);


        String url = "jdbc:mysql://localhost/title-ownership?useSSL=false";
        String user = "root";
        String password = "admin";

        if (index < 2000) {
            try {

                Connection conn = DriverManager.getConnection(url, user, password);
                String sqlQuery = "INSERT INTO person (first_name, middle_name, last_name, parcelid) values (?, ?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(sqlQuery);
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

    private static void InsertAddressObjectToDB(JSONObject address) {
        //Get employee object within list
        //JSONObject employeeObject = (JSONObject) employee.get("first_name");

        String city = (String) address.get("city");
        String street = (String) address.get("street");
        String state = (String) address.get("state");
        String zipCode = (String) address.get("zip_code");

        //System.out.println(street + "\n" + city + " , " + state + " " + zipCode+"\n");

        String url = "jdbc:mysql://localhost/title-ownership?useSSL=false";
        String user = "root";
        String password = "admin";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO parcel (street, city, state, zip_code) values (?, ?, ?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, street);
            statement.setString(2, city);
            statement.setString(3, state);
            statement.setString(4, zipCode);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("address data entered successfully");
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
