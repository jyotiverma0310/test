package com.matellio.store.web.rest;

import com.matellio.store.service.DistributionStoreService;
import com.matellio.store.web.rest.errors.BadRequestAlertException;
import com.matellio.store.service.dto.DistributionStoreDTO;

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
 * REST controller for managing {@link com.matellio.store.domain.DistributionStore}.
 */
@RestController
@RequestMapping("/api")
public class DistributionStoreResource {

    private final Logger log = LoggerFactory.getLogger(DistributionStoreResource.class);

    private static final String ENTITY_NAME = "distributionStore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DistributionStoreService distributionStoreService;

    public DistributionStoreResource(DistributionStoreService distributionStoreService) {
        this.distributionStoreService = distributionStoreService;
    }

    /**
     * {@code POST  /distribution-stores} : Create a new distributionStore.
     *
     * @param distributionStoreDTO the distributionStoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new distributionStoreDTO, or with status {@code 400 (Bad Request)} if the distributionStore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/distribution-stores")
    public ResponseEntity<DistributionStoreDTO> createDistributionStore(@RequestBody DistributionStoreDTO distributionStoreDTO) throws URISyntaxException {
        log.debug("REST request to save DistributionStore : {}", distributionStoreDTO);
        if (distributionStoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new distributionStore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DistributionStoreDTO result = distributionStoreService.save(distributionStoreDTO);
        return ResponseEntity.created(new URI("/api/distribution-stores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /distribution-stores} : Updates an existing distributionStore.
     *
     * @param distributionStoreDTO the distributionStoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distributionStoreDTO,
     * or with status {@code 400 (Bad Request)} if the distributionStoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the distributionStoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/distribution-stores")
    public ResponseEntity<DistributionStoreDTO> updateDistributionStore(@RequestBody DistributionStoreDTO distributionStoreDTO) throws URISyntaxException {
        log.debug("REST request to update DistributionStore : {}", distributionStoreDTO);
        if (distributionStoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DistributionStoreDTO result = distributionStoreService.save(distributionStoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, distributionStoreDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /distribution-stores} : get all the distributionStores.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of distributionStores in body.
     */
    @GetMapping("/distribution-stores")
    public ResponseEntity<List<DistributionStoreDTO>> getAllDistributionStores(Pageable pageable) {
        log.debug("REST request to get a page of DistributionStores");
        Page<DistributionStoreDTO> page = distributionStoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /distribution-stores/:id} : get the "id" distributionStore.
     *
     * @param id the id of the distributionStoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the distributionStoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/distribution-stores/{id}")
    public ResponseEntity<DistributionStoreDTO> getDistributionStore(@PathVariable String id) {
        log.debug("REST request to get DistributionStore : {}", id);
        Optional<DistributionStoreDTO> distributionStoreDTO = distributionStoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(distributionStoreDTO);
    }

    /**
     * {@code DELETE  /distribution-stores/:id} : delete the "id" distributionStore.
     *
     * @param id the id of the distributionStoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/distribution-stores/{id}")
    public ResponseEntity<Void> deleteDistributionStore(@PathVariable String id) {
        log.debug("REST request to delete DistributionStore : {}", id);
        distributionStoreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
