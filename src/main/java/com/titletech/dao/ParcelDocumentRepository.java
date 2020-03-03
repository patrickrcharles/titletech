package com.titletech.dao;

import com.titletech.entity.ParcelDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelDocumentRepository extends JpaRepository<ParcelDocument, Long>, ParcelDocumentRepositoryCustom {
}
