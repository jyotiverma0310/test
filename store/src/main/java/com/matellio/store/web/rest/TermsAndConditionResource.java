package com.matellio.store.web.rest;

import com.matellio.store.service.TermsAndConditionService;
import com.matellio.store.web.rest.errors.BadRequestAlertException;
import com.matellio.store.service.dto.TermsAndConditionDTO;

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
 * REST controller for managing {@link com.matellio.store.domain.TermsAndCondition}.
 */
@RestController
@RequestMapping("/api")
public class TermsAndConditionResource {

    private final Logger log = LoggerFactory.getLogger(TermsAndConditionResource.class);

    private static final String ENTITY_NAME = "termsAndCondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TermsAndConditionService termsAndConditionService;

    public TermsAndConditionResource(TermsAndConditionService termsAndConditionService) {
        this.termsAndConditionService = termsAndConditionService;
    }

    /**
     * {@code POST  /terms-and-conditions} : Create a new termsAndCondition.
     *
     * @param termsAndConditionDTO the termsAndConditionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new termsAndConditionDTO, or with status {@code 400 (Bad Request)} if the termsAndCondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/terms-and-conditions")
    public ResponseEntity<TermsAndConditionDTO> createTermsAndCondition(@RequestBody TermsAndConditionDTO termsAndConditionDTO) throws URISyntaxException {
        log.debug("REST request to save TermsAndCondition : {}", termsAndConditionDTO);
        if (termsAndConditionDTO.getId() != null) {
            throw new BadRequestAlertException("A new termsAndCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TermsAndConditionDTO result = termsAndConditionService.save(termsAndConditionDTO);
        return ResponseEntity.created(new URI("/api/terms-and-conditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /terms-and-conditions} : Updates an existing termsAndCondition.
     *
     * @param termsAndConditionDTO the termsAndConditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated termsAndConditionDTO,
     * or with status {@code 400 (Bad Request)} if the termsAndConditionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the termsAndConditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/terms-and-conditions")
    public ResponseEntity<TermsAndConditionDTO> updateTermsAndCondition(@RequestBody TermsAndConditionDTO termsAndConditionDTO) throws URISyntaxException {
        log.debug("REST request to update TermsAndCondition : {}", termsAndConditionDTO);
        if (termsAndConditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TermsAndConditionDTO result = termsAndConditionService.save(termsAndConditionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, termsAndConditionDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /terms-and-conditions} : get all the termsAndConditions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of termsAndConditions in body.
     */
    @GetMapping("/terms-and-conditions")
    public ResponseEntity<List<TermsAndConditionDTO>> getAllTermsAndConditions(Pageable pageable) {
        log.debug("REST request to get a page of TermsAndConditions");
        Page<TermsAndConditionDTO> page = termsAndConditionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /terms-and-conditions/:id} : get the "id" termsAndCondition.
     *
     * @param id the id of the termsAndConditionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the termsAndConditionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/terms-and-conditions/{id}")
    public ResponseEntity<TermsAndConditionDTO> getTermsAndCondition(@PathVariable String id) {
        log.debug("REST request to get TermsAndCondition : {}", id);
        Optional<TermsAndConditionDTO> termsAndConditionDTO = termsAndConditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(termsAndConditionDTO);
    }

    /**
     * {@code DELETE  /terms-and-conditions/:id} : delete the "id" termsAndCondition.
     *
     * @param id the id of the termsAndConditionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/terms-and-conditions/{id}")
    public ResponseEntity<Void> deleteTermsAndCondition(@PathVariable String id) {
        log.debug("REST request to delete TermsAndCondition : {}", id);
        termsAndConditionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
