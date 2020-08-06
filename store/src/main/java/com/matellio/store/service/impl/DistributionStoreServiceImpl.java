package com.matellio.store.service.impl;

import com.matellio.store.service.DistributionStoreService;
import com.matellio.store.domain.DistributionStore;
import com.matellio.store.repository.DistributionStoreRepository;
import com.matellio.store.service.dto.DistributionStoreDTO;
import com.matellio.store.service.mapper.DistributionStoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DistributionStore}.
 */
@Service
public class DistributionStoreServiceImpl implements DistributionStoreService {

    private final Logger log = LoggerFactory.getLogger(DistributionStoreServiceImpl.class);

    private final DistributionStoreRepository distributionStoreRepository;

    private final DistributionStoreMapper distributionStoreMapper;

    public DistributionStoreServiceImpl(DistributionStoreRepository distributionStoreRepository, DistributionStoreMapper distributionStoreMapper) {
        this.distributionStoreRepository = distributionStoreRepository;
        this.distributionStoreMapper = distributionStoreMapper;
    }

    @Override
    public DistributionStoreDTO save(DistributionStoreDTO distributionStoreDTO) {
        log.debug("Request to save DistributionStore : {}", distributionStoreDTO);
        DistributionStore distributionStore = distributionStoreMapper.toEntity(distributionStoreDTO);
        distributionStore = distributionStoreRepository.save(distributionStore);
        return distributionStoreMapper.toDto(distributionStore);
    }

    @Override
    public Page<DistributionStoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DistributionStores");
        return distributionStoreRepository.findAll(pageable)
            .map(distributionStoreMapper::toDto);
    }


    @Override
    public Optional<DistributionStoreDTO> findOne(String id) {
        log.debug("Request to get DistributionStore : {}", id);
        return distributionStoreRepository.findById(id)
            .map(distributionStoreMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete DistributionStore : {}", id);
        distributionStoreRepository.deleteById(id);
    }
}
