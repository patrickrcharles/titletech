package com.springboot.titletech.dao;

import com.springboot.titletech.entity.ParcelOwnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelOwnershipRepository extends JpaRepository<ParcelOwnership, Long > {
}
