package com.springboot.titletech;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;
import com.springboot.titletech.util.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StageTestData {

    public static void main(String[] args) throws java.text.ParseException, SQLException {


        int numToGenerate = 5;
        // parcel data
        ArrayList<Parcel> parcelList = new ArrayList<>();
        //parcel document data
        ArrayList<ParcelDocument> parcelDocumentsList = new ArrayList<>();
        // person data
        ArrayList<Person> personList = new ArrayList<>();
        //parcel ownership data
        ArrayList<ParcelOwnership> parcelOwnershipList = new ArrayList<>();

        // create all test data, roughly 5 function calls
        //GenerateTestData.createTestData();
        GenerateTestData3.createTestData();

        parcelDocumentsList = GenerateTestData3.getParcelDocumentsList();
        personList = GenerateTestData3.getPersonList();
        parcelList = GenerateTestData3.getParcelList();
        parcelOwnershipList = GenerateTestData3.getParcelOwnershipList();

        // in progress
        //verifyData(personList, parcelList, parcelDocumentsList, parcelOwnershipList);

        InsertTestDataToDB.InsertPersonToDB(personList);
        InsertTestDataToDB.InsertParcelToDB(parcelList);
        InsertTestDataToDB.InsertParcelDocumentToDB(parcelDocumentsList);
        InsertTestDataToDB.InsertParcelOwnershipToDB(parcelOwnershipList);

        List<Person> ppl = GetEntityObjectFromDatabase.getPersonObject();
        List<Parcel> parcels = GetEntityObjectFromDatabase.getParcelObject();
        List<ParcelDocument> parcelDocuments = GetEntityObjectFromDatabase.getParcelDocumentObject();

        //add method to remove/cleanup test data from database
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

    public static void verifyData(List<Person> personList, List<Parcel> parcelList,
                      List<ParcelDocument> parcelDocumentList,
                      List<ParcelOwnership> parcelOwnershipList)
    {
        int idToCheck = 4;
        int parcelOwnershipid = 0;

        assert(parcelDocumentList.get(idToCheck).getParcelid() == parcelList.get(idToCheck).getId());
        assert(parcelDocumentList.get(idToCheck).getCurrent_ownerid() == parcelList.get(idToCheck).getCurrent_ownerid());
        assert(parcelDocumentList.get(idToCheck).getPrevious_ownerid() == parcelList.get(idToCheck).getPrevious_ownerid());

        for (ParcelOwnership p : parcelOwnershipList) {
            if(p.getParcelid() == parcelDocumentList.get(idToCheck).getParcelid()){
                parcelOwnershipid = parcelDocumentList.get(idToCheck).getId();
                break;
            }
        }

        assert(parcelOwnershipList.get(parcelOwnershipid).getCurrent_ownerid() == parcelList.get(idToCheck).getCurrent_ownerid());
        assert(parcelOwnershipList.get(parcelOwnershipid).getPrevious_ownerid() == parcelList.get(idToCheck).getPrevious_ownerid());
        assert(parcelOwnershipList.get(parcelOwnershipid).getParcelid() == parcelList.get(idToCheck).getId());
    }
}
