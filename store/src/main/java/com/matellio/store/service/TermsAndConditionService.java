package com.matellio.store.service;

import com.matellio.store.service.dto.TermsAndConditionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.matellio.store.domain.TermsAndCondition}.
 */
public interface TermsAndConditionService {

    /**
     * Save a termsAndCondition.
     *
     * @param termsAndConditionDTO the entity to save.
     * @return the persisted entity.
     */
    TermsAndConditionDTO save(TermsAndConditionDTO termsAndConditionDTO);

    /**
     * Get all the termsAndConditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TermsAndConditionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" termsAndCondition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TermsAndConditionDTO> findOne(String id);

    /**
     * Delete the "id" termsAndCondition.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
