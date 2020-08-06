package com.matellio.store.service.impl;

import com.matellio.store.service.TermsAndConditionService;
import com.matellio.store.domain.TermsAndCondition;
import com.matellio.store.repository.TermsAndConditionRepository;
import com.matellio.store.service.dto.TermsAndConditionDTO;
import com.matellio.store.service.mapper.TermsAndConditionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TermsAndCondition}.
 */
@Service
public class TermsAndConditionServiceImpl implements TermsAndConditionService {

    private final Logger log = LoggerFactory.getLogger(TermsAndConditionServiceImpl.class);

    private final TermsAndConditionRepository termsAndConditionRepository;

    private final TermsAndConditionMapper termsAndConditionMapper;

    public TermsAndConditionServiceImpl(TermsAndConditionRepository termsAndConditionRepository, TermsAndConditionMapper termsAndConditionMapper) {
        this.termsAndConditionRepository = termsAndConditionRepository;
        this.termsAndConditionMapper = termsAndConditionMapper;
    }

    @Override
    public TermsAndConditionDTO save(TermsAndConditionDTO termsAndConditionDTO) {
        log.debug("Request to save TermsAndCondition : {}", termsAndConditionDTO);
        TermsAndCondition termsAndCondition = termsAndConditionMapper.toEntity(termsAndConditionDTO);
        termsAndCondition = termsAndConditionRepository.save(termsAndCondition);
        return termsAndConditionMapper.toDto(termsAndCondition);
    }

    @Override
    public Page<TermsAndConditionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TermsAndConditions");
        return termsAndConditionRepository.findAll(pageable)
            .map(termsAndConditionMapper::toDto);
    }


    @Override
    public Optional<TermsAndConditionDTO> findOne(String id) {
        log.debug("Request to get TermsAndCondition : {}", id);
        return termsAndConditionRepository.findById(id)
            .map(termsAndConditionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TermsAndCondition : {}", id);
        termsAndConditionRepository.deleteById(id);
    }
}
