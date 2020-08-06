package com.matellio.store.service.impl;

import com.matellio.store.service.WholesalerService;
import com.matellio.store.domain.Wholesaler;
import com.matellio.store.repository.WholesalerRepository;
import com.matellio.store.service.dto.WholesalerDTO;
import com.matellio.store.service.mapper.WholesalerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Wholesaler}.
 */
@Service
public class WholesalerServiceImpl implements WholesalerService {

    private final Logger log = LoggerFactory.getLogger(WholesalerServiceImpl.class);

    private final WholesalerRepository wholesalerRepository;

    private final WholesalerMapper wholesalerMapper;

    public WholesalerServiceImpl(WholesalerRepository wholesalerRepository, WholesalerMapper wholesalerMapper) {
        this.wholesalerRepository = wholesalerRepository;
        this.wholesalerMapper = wholesalerMapper;
    }

    @Override
    public WholesalerDTO save(WholesalerDTO wholesalerDTO) {
        log.debug("Request to save Wholesaler : {}", wholesalerDTO);
        Wholesaler wholesaler = wholesalerMapper.toEntity(wholesalerDTO);
        wholesaler = wholesalerRepository.save(wholesaler);
        return wholesalerMapper.toDto(wholesaler);
    }

    @Override
    public Page<WholesalerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Wholesalers");
        return wholesalerRepository.findAll(pageable)
            .map(wholesalerMapper::toDto);
    }


    @Override
    public Optional<WholesalerDTO> findOne(String id) {
        log.debug("Request to get Wholesaler : {}", id);
        return wholesalerRepository.findById(id)
            .map(wholesalerMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Wholesaler : {}", id);
        wholesalerRepository.deleteById(id);
    }
}
