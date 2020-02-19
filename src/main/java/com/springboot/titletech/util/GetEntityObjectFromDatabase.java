package com.springboot.titletech.util;

import com.springboot.titletech.dao.PersonDAO;
import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.Person;
import com.springboot.titletech.service.PersonServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetEntityObjectFromDatabase {
    // database connect info
    private final static String url = "jdbc:mysql://localhost/title-ownership?useSSL=false";
    private final static String user = "root";
    private final static String password = "admin";

    private final static String getPersonObjectQuery = "SELECT * from person;";
    private final static String getPersonNameQuery = "SELECT first_name, middle_name, last_name from person where id=";
    private final static String getParcelObjectQuery = "SELECT * from parcel;";
    private final static String getParcelDocumentObjectQuery = "SELECT * from parcel_document;";

    public static List<Person> getPersonObject() throws SQLException {

        List<Person> personList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(getPersonObjectQuery);
            ResultSet rs = statement.executeQuery(getPersonObjectQuery);

            while (rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("first_name");
                String middle = rs.getString("middle_name");
                String last = rs.getString("last_name");

                Person person = new Person();
                person.setId(id);
                person.setFirstName(first);
                person.setMiddleName(middle);
                person.setLastName(last);

                personList.add(person);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personList;
    }

    public static List<Parcel> getParcelObject() throws SQLException {

        List<Parcel> parcelList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(getParcelObjectQuery);
            ResultSet rs = statement.executeQuery(getParcelObjectQuery);

            while (rs.next()) {

                int id = rs.getInt("id");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipCode = rs.getString("zip_code");
                int currentOwnerId = rs.getInt("current_ownerid");
                int previousOwnerId = rs.getInt("previous_ownerid");

                Parcel parcel = new Parcel();
                parcel.setId(id);
                parcel.setStreet(street);
                parcel.setCity(city);
                parcel.setState(state);
                parcel.setZipCode(zipCode);
                parcel.setCurrent_ownerid(currentOwnerId);
                parcel.setPrevious_ownerid(previousOwnerId);
                parcelList.add(parcel);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelList;
    }

    public static List<ParcelDocument> getParcelDocumentObject() throws SQLException {

        List<ParcelDocument> ParcelDocumentList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(getParcelDocumentObjectQuery);
            ResultSet rs = statement.executeQuery(getParcelDocumentObjectQuery);

            while (rs.next()) {

                int id = rs.getInt("id");
                int parcelid = rs.getInt("parcelid");
                String datePurchased = rs.getString("date_purchased");
                String dateSold = rs.getString("date_sold");
                int currentOwnerId = rs.getInt("current_ownerid");
                int previousOwnerId = rs.getInt("previous_ownerid");

                ParcelDocument parcelDocument = new ParcelDocument();
                parcelDocument.setId(id);
                parcelDocument.setParcelid(parcelid);
                parcelDocument.setDatePurchased(datePurchased);
                parcelDocument.setDateSold(dateSold);
                parcelDocument.setCurrent_ownerid(currentOwnerId);
                parcelDocument.setPrevious_ownerid(previousOwnerId);

                ParcelDocumentList.add(parcelDocument);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ParcelDocumentList;
    }
//    public static String getPersonNameById( int personid) throws SQLException {
//
//        Person person = new Person();
//        try {
//            Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement statement = conn.prepareStatement(getPersonNameQuery+personid);
//            ResultSet rs = statement.executeQuery(getPersonNameQuery+personid);
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                if(id == personid) {
//                    String first = rs.getString("first_name");
//                    String middle = rs.getString("middle_name");
//                    String last = rs.getString("last_name");
//
//                    person.setId(id);
//                    person.setFirstName(first);
//                    person.setMiddleName(middle);
//                    person.setLastName(last);
//                }
//
//            }
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName();
//    }
}
