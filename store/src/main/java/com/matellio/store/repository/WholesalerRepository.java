package com.matellio.store.repository;

import com.matellio.store.domain.Wholesaler;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Wholesaler entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WholesalerRepository extends MongoRepository<Wholesaler, String> {
}
