package com.springboot.titletech.util;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertTestDataToDB {

    // database connect info
    protected static String url = "jdbc:mysql://localhost/title-ownership?useSSL=false";
    protected static String user = "root";
    protected static String password = "admin";

    //    protected static String insertPersonDataQuery =
//            "INSERT INTO person (first_name, middle_name, last_name, date_purchased, date_sold, parcelid) values (?, ?, ?, ?,?,?)";
    protected static String insertPersonDataQuery =
            "INSERT INTO person (parcelid, first_name, middle_name, last_name, date_purchased, date_sold) values (?, ?, ?, ?, ?, ?)";

    protected static String insertParcelDataQuery = "INSERT INTO parcel (street, city, state, zip_code,previous_ownerid, current_ownerid) " +
            "values (?, ?, ?,?,?,?)";
    protected static String insertParceDocumentlDataQuery =
            "INSERT INTO parcel_document (parcelid, date_purchased, date_sold, current_ownerid, previous_ownerid, current_owner, previous_owner)" +
                    " values (?, ?, ?,? ,?, ? , ?)";
    protected static String insertParceOwnershiplDataQuery =
            "INSERT INTO parcel_ownership (current_ownerid, previous_ownerid, parcelid, parcel_documentid, date_purchased, date_sold) values (?, ?, ?, ?, ?, ?)";


    public static void InsertParcelOwnershipToDB(ArrayList<ParcelOwnership> parcelOwnershipList) {

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            for (ParcelOwnership p : parcelOwnershipList
            ) {
                PreparedStatement statement = conn.prepareStatement(insertParceOwnershiplDataQuery);
                statement.setInt(1, p.getCurrent_ownerid());
                statement.setInt(2, p.getPrevious_ownerid());
                statement.setInt(3, p.getParcelid());
                statement.setInt(4, p.getParcelDocumentid());
                statement.setString(5, p.getDatePurchased());
                statement.setString(6, p.getDateSold());

                int row = statement.executeUpdate();
                if (row > 0) {
                    System.out.println("Parcel Ownership data entered successfully");
                }
                statement.close();
            }
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void InsertParcelDocumentToDB(List<ParcelDocument> parcelDocumentList) {

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
                statement.setString(6, p.getCurrent_owner());
                statement.setString(7, p.getPrevious_owner());

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

    public static void InsertParcelToDB(ArrayList<Parcel> parcelList) {

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
                statement.setInt(1, p.getParcelid());
                statement.setString(2, p.getFirstName());
                statement.setString(3, p.getMiddleName());
                statement.setString(4, p.getLastName());
                statement.setString(5, p.getDatePurchased());
                statement.setString(6, p.getDateSold());

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
}
