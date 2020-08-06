package com.matellio.store.web.rest;

import com.matellio.store.service.ProductSubCategoryService;
import com.matellio.store.web.rest.errors.BadRequestAlertException;
import com.matellio.store.service.dto.ProductSubCategoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.matellio.store.domain.ProductSubCategory}.
 */
@RestController
@RequestMapping("/api")
public class ProductSubCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ProductSubCategoryResource.class);

    private static final String ENTITY_NAME = "productSubCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductSubCategoryService productSubCategoryService;

    public ProductSubCategoryResource(ProductSubCategoryService productSubCategoryService) {
        this.productSubCategoryService = productSubCategoryService;
    }

    /**
     * {@code POST  /product-sub-categories} : Create a new productSubCategory.
     *
     * @param productSubCategoryDTO the productSubCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productSubCategoryDTO, or with status {@code 400 (Bad Request)} if the productSubCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-sub-categories")
    public ResponseEntity<ProductSubCategoryDTO> createProductSubCategory(@RequestBody ProductSubCategoryDTO productSubCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save ProductSubCategory : {}", productSubCategoryDTO);
        if (productSubCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new productSubCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductSubCategoryDTO result = productSubCategoryService.save(productSubCategoryDTO);
        return ResponseEntity.created(new URI("/api/product-sub-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /product-sub-categories} : Updates an existing productSubCategory.
     *
     * @param productSubCategoryDTO the productSubCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productSubCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the productSubCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productSubCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-sub-categories")
    public ResponseEntity<ProductSubCategoryDTO> updateProductSubCategory(@RequestBody ProductSubCategoryDTO productSubCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update ProductSubCategory : {}", productSubCategoryDTO);
        if (productSubCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductSubCategoryDTO result = productSubCategoryService.save(productSubCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productSubCategoryDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /product-sub-categories} : get all the productSubCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productSubCategories in body.
     */
    @GetMapping("/product-sub-categories")
    public ResponseEntity<List<ProductSubCategoryDTO>> getAllProductSubCategories(Pageable pageable) {
        log.debug("REST request to get a page of ProductSubCategories");
        Page<ProductSubCategoryDTO> page = productSubCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-sub-categories/:id} : get the "id" productSubCategory.
     *
     * @param id the id of the productSubCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productSubCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-sub-categories/{id}")
    public ResponseEntity<ProductSubCategoryDTO> getProductSubCategory(@PathVariable String id) {
        log.debug("REST request to get ProductSubCategory : {}", id);
        Optional<ProductSubCategoryDTO> productSubCategoryDTO = productSubCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productSubCategoryDTO);
    }

    /**
     * {@code DELETE  /product-sub-categories/:id} : delete the "id" productSubCategory.
     *
     * @param id the id of the productSubCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-sub-categories/{id}")
    public ResponseEntity<Void> deleteProductSubCategory(@PathVariable String id) {
        log.debug("REST request to delete ProductSubCategory : {}", id);
        productSubCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
