package com.matellio.store.repository;

import com.matellio.store.domain.AddOnProduct;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AddOnProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddOnProductRepository extends MongoRepository<AddOnProduct, String> {
}
