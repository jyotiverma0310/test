package com.matellio.store.repository;

import com.matellio.store.domain.ProductSubCategory;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductSubCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSubCategoryRepository extends MongoRepository<ProductSubCategory, String> {
}
