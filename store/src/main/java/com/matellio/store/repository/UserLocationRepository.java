package com.matellio.store.repository;

import com.matellio.store.domain.UserLocation;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the UserLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserLocationRepository extends MongoRepository<UserLocation, String> {
}
