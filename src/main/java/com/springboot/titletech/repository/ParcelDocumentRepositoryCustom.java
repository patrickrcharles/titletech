package com.springboot.titletech.repository;

import com.springboot.titletech.entity.ParcelDocument;

import java.util.List;

public interface ParcelDocumentRepositoryCustom {

    List<ParcelDocument> findParcelDocumentsByParcelID(int parcelId);
}
