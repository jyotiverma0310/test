package com.matellio.store.service;

import com.matellio.store.service.dto.ProductSubCategoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.matellio.store.domain.ProductSubCategory}.
 */
public interface ProductSubCategoryService {

    /**
     * Save a productSubCategory.
     *
     * @param productSubCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    ProductSubCategoryDTO save(ProductSubCategoryDTO productSubCategoryDTO);

    /**
     * Get all the productSubCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductSubCategoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productSubCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductSubCategoryDTO> findOne(String id);

    /**
     * Delete the "id" productSubCategory.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
