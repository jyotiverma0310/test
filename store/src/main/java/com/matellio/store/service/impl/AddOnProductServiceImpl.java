package com.matellio.store.service.impl;

import com.matellio.store.service.AddOnProductService;
import com.matellio.store.domain.AddOnProduct;
import com.matellio.store.repository.AddOnProductRepository;
import com.matellio.store.service.dto.AddOnProductDTO;
import com.matellio.store.service.mapper.AddOnProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AddOnProduct}.
 */
@Service
public class AddOnProductServiceImpl implements AddOnProductService {

    private final Logger log = LoggerFactory.getLogger(AddOnProductServiceImpl.class);

    private final AddOnProductRepository addOnProductRepository;

    private final AddOnProductMapper addOnProductMapper;

    public AddOnProductServiceImpl(AddOnProductRepository addOnProductRepository, AddOnProductMapper addOnProductMapper) {
        this.addOnProductRepository = addOnProductRepository;
        this.addOnProductMapper = addOnProductMapper;
    }

    @Override
    public AddOnProductDTO save(AddOnProductDTO addOnProductDTO) {
        log.debug("Request to save AddOnProduct : {}", addOnProductDTO);
        AddOnProduct addOnProduct = addOnProductMapper.toEntity(addOnProductDTO);
        addOnProduct = addOnProductRepository.save(addOnProduct);
        return addOnProductMapper.toDto(addOnProduct);
    }

    @Override
    public Page<AddOnProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AddOnProducts");
        return addOnProductRepository.findAll(pageable)
            .map(addOnProductMapper::toDto);
    }


    @Override
    public Optional<AddOnProductDTO> findOne(String id) {
        log.debug("Request to get AddOnProduct : {}", id);
        return addOnProductRepository.findById(id)
            .map(addOnProductMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete AddOnProduct : {}", id);
        addOnProductRepository.deleteById(id);
    }
}
