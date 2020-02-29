package com.springboot.titletech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelOwnershipRepository extends JpaRepository<ParcelOwnershipRepository, Long > {
}
