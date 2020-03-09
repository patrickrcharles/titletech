//package com.titletech.util;
//
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//
//public class ReadJsonFromFile {
//
//    public ReadJsonFromFile() {
//
//        // saving this code for future reference to create json file reader
//
//        // get json file paths from resource folder. this is good enough for now
//        // todo: write a class to load properties file
//
//        //get paths to json test data files
//        ClassLoader classLoader = ReadJsonFromFile.class.getClassLoader();
//        String jsonNamesPath = classLoader.getResource("names.json").getPath();
////        String jsonAddressesPath = classLoader.getResource("addresses.json").getPath();
////        String jsonBoughtSoldTimestampsPath = classLoader.getResource("timestamps.json").getPath();
//
//        // get jsonArrays of test data for parsing/insertion to database
//        JSONArray personList = getJsonArrayFromFile(jsonNamesPath);
////        JSONArray parcelList = getJsonArrayFromFile(jsonAddressesPath);
////        JSONArray boughtSoldTimestampsList = getJsonArrayFromFile(jsonBoughtSoldTimestampsPath);
//
//        // timestamps is going to a problem
//        /*
//        first need to create an array that makes sure data is organized by lowest date first
//        identify parcels with multiple owners
//        apply timestamps to that
//         */
//
//        // insert person data into array
//        // personIndex is for keeping track of the index of the person object in the jsonArray
//        final int[] personIndex = new int[2000];
//        assert personList != null;
//        personList.forEach((Object person) -> {
//            InsertPersonObjectToDB((JSONObject) person, personIndex[0]);
//            // increment for next person.
//            personIndex[0]++;
//        });
//
//        // timestamps is going to a problem
//        /*
//        first need to create an array that makes sure data is organized by lowest date first
//        identify parcels with multiple owners
//        apply timestamps to that
//         */
////        assert boughtSoldTimestampsList != null;
////        boughtSoldTimestampsList.forEach((Object timestamp) -> {
////            try {
////                InsertBoughtSoldTimeStampsObjectToDB((JSONObject) timestamp);
////            } catch (java.text.ParseException e) {
////                e.printStackTrace();
////            }
////        });
//
////        // insert Parcel data into database
////        parcelList.forEach(parcel -> {
////            InsertParcelObjectToDB((JSONObject) parcel);
////        });
//
//        // insert parcel dates into person based on parcelid
//        // where person.parcelid = parcel.id
//        //      insert person.name = parcel.currentOwner.
////
////        InputStream in = this.getClass().getClassLoader().getResourceAsStream("names_test_data.json");
////
//    }
//
//    private static JSONArray getJsonArrayFromFile(String jsonNamesPath) {
//
//        JSONParser parser = new JSONParser();
//        try (Reader reader = new FileReader(jsonNamesPath)) {
//
//            Object obj = parser.parse(reader);
//
//            return (JSONArray) obj;
//
//        } catch (IOException | ParseException exception) {
//            exception.printStackTrace();
//            return null;
//        }
//    }
//}
////        private static void InsertPersonObjectToDB(JSONObject person, int index) {
////
////            String firstName = (String) person.get("first_name");
////            String lastName = (String) person.get("last_name");
////            String middleName = (String) person.get("middle_name");
////
////            int[] parcelid = getShuffledIntegerArray(2000);
////
////            if (index >= 2000) {
////                index = index - 2000;
////                //another hack. 5000 persons, 2000 addresses.
////                // reset index manually so we can have duplicate addresses
////            }
////
////            // todo: this is a hack. for some reason data inserts correctly but index still hits 2000 and throws
////            // out of bounds error. figure it out later
////
////            if (index < 2000) {
////                try {
////
////                    Connection conn = DriverManager.getConnection(url, user, password);
////
////                    PreparedStatement statement = conn.prepareStatement(insertPersonDataQuery);
////                    statement.setString(1, firstName);
////                    statement.setString(2, middleName);
////                    statement.setString(3, lastName);
////                    statement.setInt(4, parcelid[index]);
////
////                    int row = statement.executeUpdate();
////                    if (row > 0) {
////                        System.out.println("Person data entered successfully");
////                    }
////                    statement.close();
////                    conn.close();
////                } catch (SQLException ex) {
////                    ex.printStackTrace();
////                }
////            }
////        }
////
////        private static void InsertParcelObjectToDB(JSONObject address) {
////            //Get employee object within list
////            //JSONObject employeeObject = (JSONObject) employee.get("first_name");
////
////            String city = (String) address.get("city");
////            String street = (String) address.get("street");
////            String state = (String) address.get("state");
////            String zipCode = (String) address.get("zip_code");
////
////            try {
////                Connection conn = DriverManager.getConnection(url, user, password);
////
////                PreparedStatement statement = conn.prepareStatement(insertParcelDataQuery);
////                statement.setString(1, street);
////                statement.setString(2, city);
////                statement.setString(3, state);
////                statement.setString(4, zipCode);
////
////                int row = statement.executeUpdate();
////                if (row > 0) {
////                    System.out.println("parcel data entered successfully");
////                }
////                statement.close();
////                conn.close();
////            } catch (SQLException ex) {
////                ex.printStackTrace();
////            }
////        }
////
////        private static void InsertBoughtSoldTimeStampsObjectToDB(JSONObject object) throws java.text.ParseException {
////
////            String datePurchased = (String) object.get("date_purchased");
////            String dateSold = (String) object.get("date_sold");
////
////            // needed to insert to database
////            java.sql.Date sqlDatePurchased;
////            java.sql.Date sqlDateSold;
////
////            //String linebreak = null;
////
////            Date purchased = new SimpleDateFormat("dd/MM/yyyy").parse(datePurchased);
////            Date sold = new SimpleDateFormat("dd/MM/yyyy").parse(dateSold);
////
////            if (purchased.after(sold)) {
////                //System.out.print("\n XXX - datePurchased after before dateSold");
////                //copy original values
////                Date originalDatePurchased = purchased;
////                //Date originalDateSold = sold;
////                //swap values
////                purchased = sold;
////                sold = originalDatePurchased;
////
////                //convert to sql Date
////                sqlDatePurchased = new java.sql.Date(purchased.getTime());
////                sqlDateSold = new java.sql.Date(sold.getTime());
////
////            } else {
////                sqlDatePurchased = new java.sql.Date(purchased.getTime());
////                sqlDateSold = new java.sql.Date(sold.getTime());
////            }
////            // next, insert into parcel table into database
////            // then, use parcelids to insert into personid
////            // test with maybe 10-20 first
////            try {
////                Connection conn = DriverManager.getConnection(url, user, password);
////
////                PreparedStatement statement = conn.prepareStatement(insertTimestampDataQuery);
////                statement.setDate(1, sqlDatePurchased);
////                statement.setDate(2, sqlDateSold);
////
////                int row = statement.executeUpdate();
////                if (row > 0) {
////                    System.out.println("timestamp data entered successfully");
////                }
////                statement.close();
////                conn.close();
////            } catch (SQLException ex) {
////                ex.printStackTrace();
////            }
////        }
////
//
