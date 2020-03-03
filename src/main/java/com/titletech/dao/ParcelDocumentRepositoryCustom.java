package com.titletech.dao;

import com.titletech.entity.ParcelDocument;

import java.util.List;

public interface ParcelDocumentRepositoryCustom {

    List<ParcelDocument> findParcelDocumentsByParcelID(int parcelId);
}
