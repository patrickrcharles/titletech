package com.springboot.titletech;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;
import com.springboot.titletech.util.GenerateTestData;
import com.springboot.titletech.util.GetEntityObjectFromDatabase;
import com.springboot.titletech.util.InsertTestDataToDB;
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

    public static void main(String[] args) throws java.text.ParseException, SQLException {


        int numToGenerate = 5;
        // for parcel data
        ArrayList<Parcel> parcelList = new ArrayList<>();
        //for  parcel document data
        ArrayList<ParcelDocument> parcelDocumentsList = new ArrayList<>();
        ArrayList<Person> personList = new ArrayList<>();
        ArrayList<ParcelOwnership> parcelOwnershipList = new ArrayList<>();

        for (int j = 0; j < numToGenerate; j++) {
            parcelList.add(GenerateTestData.generateParcelList(j + 1));
            parcelDocumentsList.add(GenerateTestData.generateParcelDocumentsList( parcelList, j));
            personList.add(GenerateTestData.generatePersonList(j));
            parcelOwnershipList.add(GenerateTestData.generateParcelOwnershipList(j, personList, parcelDocumentsList));
        }

        // in progress
        verifyData(personList, parcelList, parcelDocumentsList, parcelOwnershipList);

        InsertTestDataToDB.InsertParcelToDB(parcelList);
        InsertTestDataToDB.InsertParcelDocumentToDB(parcelDocumentsList);
        InsertTestDataToDB.InsertPersonToDB(personList);
        InsertTestDataToDB.InsertParcelOwnershipToDB(parcelOwnershipList);

        GetEntityObjectFromDatabase util = new GetEntityObjectFromDatabase();
        List<Person> ppl = util.getPersonObject();
        List<Parcel> parcels = util.getParcelObject();
        List<ParcelDocument> parcelDocuments = util.getParcelDocumentObject();
        
        //add method to remove data from database
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
