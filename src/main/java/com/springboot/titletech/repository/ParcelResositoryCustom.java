package com.springboot.titletech.repository;

import com.springboot.titletech.entity.ParcelOwnership;
import com.springboot.titletech.entity.Person;

import java.util.List;

public interface ParcelResositoryCustom {

    List<Person> findCurrentOwnerByParcelId(int theId);

    List<Person> findPreviousOwnerByParcelId(int theId);

    List<ParcelOwnership> findParcelHistory(int theId);
}
