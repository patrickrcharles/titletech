package com.springboot.titletech.dao;

import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;

import java.util.List;

public interface ParcelRepositoryCustom {

    List<Person> findCurrentOwnerByParcelId(int theId);

    List<Person> findPreviousOwnerByParcelId(int theId);

    List<ParcelOwnership> findParcelHistory(int theId);
}
