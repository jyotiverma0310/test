package com.matellio.store.service;

import com.matellio.store.service.dto.WholesalerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.matellio.store.domain.Wholesaler}.
 */
public interface WholesalerService {

    /**
     * Save a wholesaler.
     *
     * @param wholesalerDTO the entity to save.
     * @return the persisted entity.
     */
    WholesalerDTO save(WholesalerDTO wholesalerDTO);

    /**
     * Get all the wholesalers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WholesalerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wholesaler.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WholesalerDTO> findOne(String id);

    /**
     * Delete the "id" wholesaler.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
