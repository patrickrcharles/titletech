package com.titletech.dao;

import com.titletech.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long>, ParcelRepositoryCustom {

}
