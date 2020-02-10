package com.springboot.titletech.dao;

import com.springboot.titletech.entity.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();

    Person findById(int theId);

    void save(Person person);

    void deleteById(int theId);
}
