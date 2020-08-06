package com.matellio.store.repository;

import com.matellio.store.domain.TermsAndCondition;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TermsAndCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermsAndConditionRepository extends MongoRepository<TermsAndCondition, String> {
}
