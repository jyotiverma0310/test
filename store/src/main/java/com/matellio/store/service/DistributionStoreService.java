package com.matellio.store.service;

import com.matellio.store.service.dto.DistributionStoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.matellio.store.domain.DistributionStore}.
 */
public interface DistributionStoreService {

    /**
     * Save a distributionStore.
     *
     * @param distributionStoreDTO the entity to save.
     * @return the persisted entity.
     */
    DistributionStoreDTO save(DistributionStoreDTO distributionStoreDTO);

    /**
     * Get all the distributionStores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DistributionStoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" distributionStore.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DistributionStoreDTO> findOne(String id);

    /**
     * Delete the "id" distributionStore.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
