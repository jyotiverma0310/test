package com.matellio.store.service;

import com.matellio.store.service.dto.AddOnProductDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.matellio.store.domain.AddOnProduct}.
 */
public interface AddOnProductService {

    /**
     * Save a addOnProduct.
     *
     * @param addOnProductDTO the entity to save.
     * @return the persisted entity.
     */
    AddOnProductDTO save(AddOnProductDTO addOnProductDTO);

    /**
     * Get all the addOnProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AddOnProductDTO> findAll(Pageable pageable);


    /**
     * Get the "id" addOnProduct.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AddOnProductDTO> findOne(String id);

    /**
     * Delete the "id" addOnProduct.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
