package com.matellio.store.service.impl;

import com.matellio.store.service.ProductSubCategoryService;
import com.matellio.store.domain.ProductSubCategory;
import com.matellio.store.repository.ProductSubCategoryRepository;
import com.matellio.store.service.dto.ProductSubCategoryDTO;
import com.matellio.store.service.mapper.ProductSubCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductSubCategory}.
 */
@Service
public class ProductSubCategoryServiceImpl implements ProductSubCategoryService {

    private final Logger log = LoggerFactory.getLogger(ProductSubCategoryServiceImpl.class);

    private final ProductSubCategoryRepository productSubCategoryRepository;

    private final ProductSubCategoryMapper productSubCategoryMapper;

    public ProductSubCategoryServiceImpl(ProductSubCategoryRepository productSubCategoryRepository, ProductSubCategoryMapper productSubCategoryMapper) {
        this.productSubCategoryRepository = productSubCategoryRepository;
        this.productSubCategoryMapper = productSubCategoryMapper;
    }

    @Override
    public ProductSubCategoryDTO save(ProductSubCategoryDTO productSubCategoryDTO) {
        log.debug("Request to save ProductSubCategory : {}", productSubCategoryDTO);
        ProductSubCategory productSubCategory = productSubCategoryMapper.toEntity(productSubCategoryDTO);
        productSubCategory = productSubCategoryRepository.save(productSubCategory);
        return productSubCategoryMapper.toDto(productSubCategory);
    }

    @Override
    public Page<ProductSubCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductSubCategories");
        return productSubCategoryRepository.findAll(pageable)
            .map(productSubCategoryMapper::toDto);
    }


    @Override
    public Optional<ProductSubCategoryDTO> findOne(String id) {
        log.debug("Request to get ProductSubCategory : {}", id);
        return productSubCategoryRepository.findById(id)
            .map(productSubCategoryMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ProductSubCategory : {}", id);
        productSubCategoryRepository.deleteById(id);
    }
}
