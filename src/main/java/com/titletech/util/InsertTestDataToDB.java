package com.titletech.util;

import com.titletech.entity.Parcel;
import com.titletech.entity.ParcelDocument;
import com.titletech.entity.ParcelOwnership;
import com.titletech.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertTestDataToDB {

    // database connect info
    protected static String url = "jdbc:mysql://localhost/titletech-ownership?useSSL=false";
    protected static String user = "root";
    protected static String password = "admin";

    final static String insertPersonDataQuery =
            "INSERT INTO person (parcelid, first_name, middle_name, last_name, date_purchased, date_sold, is_current_owner) values (?, ?, ?, ?, ?, ?, ?)";
    final static String insertParcelDataQuery = "INSERT INTO parcel (street, city, state, zip_code,previous_ownerid, current_ownerid) " +
            "values (?, ?, ?, ?, ?, ?)";
    final static String insertParceDocumentlDataQuery =
            "INSERT INTO parcel_document (parcelid, date_purchased, date_sold, current_ownerid, previous_ownerid, current_owner, previous_owner)" +
                    " values (?, ?, ?,? ,?, ? , ?)";
    final static String insertParceOwnershiplDataQuery =
            "INSERT INTO parcel_ownership (current_ownerid, previous_ownerid, parcelid, parcel_documentid, " +
                    "date_purchased, date_sold,current_owner, previous_owner) values (?, ?, ?, ?, ?, ?, ? , ?)";

    public static void InsertParcelOwnershipToDB(ArrayList<ParcelOwnership> parcelOwnershipList) {

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            for (ParcelOwnership p : parcelOwnershipList
            ) {
                PreparedStatement statement = conn.prepareStatement(insertParceOwnershiplDataQuery);
                statement.setInt(1, p.getCurrentOwnerid());
                statement.setInt(2, p.getPreviousOwnerid());
                statement.setInt(3, p.getParcelid());
                statement.setInt(4, p.getParcelDocumentid());
                statement.setString(5, p.getDatePurchased());
                statement.setString(6, p.getDateSold());
                statement.setString(7, p.getCurrentOwner());
                statement.setString(8, p.getPreviousOwner());

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
                statement.setInt(4, p.getCurrentOwnerid());
                statement.setInt(5, p.getPreviousOwnerid());
                statement.setString(6, p.getCurrentOwner());
                statement.setString(7, p.getPreviousOwner());

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
                statement.setInt(5, p.getPreviousOwnerid());
                statement.setInt(6, p.getCurrentOwnerid());

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
                statement.setInt(7, p.getIsCurrentOwner());

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
