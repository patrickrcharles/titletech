package com.titletech.dao;

import com.titletech.entity.ParcelOwnership;
import com.titletech.entity.Person;

import java.util.List;

public interface ParcelRepositoryCustom {

    List<Person> findCurrentOwnerByParcelId(int theId);

    List<Person> findPreviousOwnerByParcelId(int theId);

    List<ParcelOwnership> findParcelHistory(int theId);
}
