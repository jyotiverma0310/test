package com.matellio.store.repository;

import com.matellio.store.domain.DistributionStore;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DistributionStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistributionStoreRepository extends MongoRepository<DistributionStore, String> {
}
