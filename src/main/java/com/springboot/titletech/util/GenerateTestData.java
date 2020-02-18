package com.springboot.titletech.util;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GenerateTestData {

    protected static String SOURCES_ALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    protected static String SOURCES_NUMBERS = "1234567890";
    protected static String SOURCES_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static ParcelOwnership generateParcelOwnershipList(int index, ArrayList<Person> personList, ArrayList<ParcelDocument> parcelDocumentList) {

        ParcelOwnership parcelOwnership = new ParcelOwnership();

        for (ParcelDocument parcelDocument: parcelDocumentList) {

            parcelOwnership.setId(index+1);
            parcelOwnership.setParcelid(parcelDocumentList.get(index).getParcelid());
            parcelOwnership.setPrevious_ownerid(parcelDocumentList.get(index).getPrevious_ownerid());
            parcelOwnership.setCurrent_ownerid(parcelDocumentList.get(index).getCurrent_ownerid());
            parcelOwnership.setParcelDocumentid(parcelDocumentList.get(index).getId());
            parcelOwnership.setDatePurchased(parcelDocumentList.get(index).getDatePurchased());
            parcelOwnership.setDateSold(parcelDocumentList.get(index).getDateSold());

        }

        return parcelOwnership;
    }

    public static ParcelDocument generateParcelDocumentsList(List<Parcel> parcelList, int index) throws java.text.ParseException {

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
        parcelDocument.setId(index+1);
        parcelDocument.setParcelid(index+1);
        parcelDocument.setDatePurchased(purchased.toString());
        parcelDocument.setDateSold(sold.toString());
        parcelDocument.setCurrent_ownerid(parcelList.get(index).getCurrent_ownerid());
        parcelDocument.setPrevious_ownerid(parcelList.get(index).getPrevious_ownerid());

        return parcelDocument;
    }

    public static Parcel generateParcelList(int index) {

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

    public static Person generatePersonList(int index) {

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

    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static String createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day).toString();
    }

    private static String generateString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}
