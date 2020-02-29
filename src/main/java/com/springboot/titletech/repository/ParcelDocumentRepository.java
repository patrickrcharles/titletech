package com.springboot.titletech.repository;

import com.springboot.titletech.entity.Parcel;
import com.springboot.titletech.entity.ParcelDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelDocumentRepository extends JpaRepository<ParcelDocument, Long> {
}
