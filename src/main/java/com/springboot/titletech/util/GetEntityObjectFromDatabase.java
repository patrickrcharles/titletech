package com.springboot.titletech.util;

import com.springboot.titletech.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetEntityObjectFromDatabase {
    // database connect info
    protected static String url = "jdbc:mysql://localhost/title-ownership?useSSL=false";
    protected static String user = "root";
    protected static String password = "admin";

    String getPersonObjectQuery = "SELECT * from person;";

    public List<Person> getPersonObject() throws SQLException {

        List<Person> personList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(getPersonObjectQuery);
            ResultSet rs = statement.executeQuery(getPersonObjectQuery);

            while (rs.next()) {
                String first = rs.getString("first_name");
                String middle = rs.getString("middle_name");
                String last = rs.getString("last_name");

                Person person = new Person();
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
}
