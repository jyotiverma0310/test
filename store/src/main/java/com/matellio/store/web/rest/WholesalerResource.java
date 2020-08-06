package com.matellio.store.web.rest;

import com.matellio.store.service.WholesalerService;
import com.matellio.store.web.rest.errors.BadRequestAlertException;
import com.matellio.store.service.dto.WholesalerDTO;

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
 * REST controller for managing {@link com.matellio.store.domain.Wholesaler}.
 */
@RestController
@RequestMapping("/api")
public class WholesalerResource {

    private final Logger log = LoggerFactory.getLogger(WholesalerResource.class);

    private static final String ENTITY_NAME = "wholesaler";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WholesalerService wholesalerService;

    public WholesalerResource(WholesalerService wholesalerService) {
        this.wholesalerService = wholesalerService;
    }

    /**
     * {@code POST  /wholesalers} : Create a new wholesaler.
     *
     * @param wholesalerDTO the wholesalerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wholesalerDTO, or with status {@code 400 (Bad Request)} if the wholesaler has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wholesalers")
    public ResponseEntity<WholesalerDTO> createWholesaler(@RequestBody WholesalerDTO wholesalerDTO) throws URISyntaxException {
        log.debug("REST request to save Wholesaler : {}", wholesalerDTO);
        if (wholesalerDTO.getId() != null) {
            throw new BadRequestAlertException("A new wholesaler cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WholesalerDTO result = wholesalerService.save(wholesalerDTO);
        return ResponseEntity.created(new URI("/api/wholesalers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /wholesalers} : Updates an existing wholesaler.
     *
     * @param wholesalerDTO the wholesalerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wholesalerDTO,
     * or with status {@code 400 (Bad Request)} if the wholesalerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wholesalerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wholesalers")
    public ResponseEntity<WholesalerDTO> updateWholesaler(@RequestBody WholesalerDTO wholesalerDTO) throws URISyntaxException {
        log.debug("REST request to update Wholesaler : {}", wholesalerDTO);
        if (wholesalerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WholesalerDTO result = wholesalerService.save(wholesalerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wholesalerDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /wholesalers} : get all the wholesalers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wholesalers in body.
     */
    @GetMapping("/wholesalers")
    public ResponseEntity<List<WholesalerDTO>> getAllWholesalers(Pageable pageable) {
        log.debug("REST request to get a page of Wholesalers");
        Page<WholesalerDTO> page = wholesalerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wholesalers/:id} : get the "id" wholesaler.
     *
     * @param id the id of the wholesalerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wholesalerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wholesalers/{id}")
    public ResponseEntity<WholesalerDTO> getWholesaler(@PathVariable String id) {
        log.debug("REST request to get Wholesaler : {}", id);
        Optional<WholesalerDTO> wholesalerDTO = wholesalerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wholesalerDTO);
    }

    /**
     * {@code DELETE  /wholesalers/:id} : delete the "id" wholesaler.
     *
     * @param id the id of the wholesalerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wholesalers/{id}")
    public ResponseEntity<Void> deleteWholesaler(@PathVariable String id) {
        log.debug("REST request to delete Wholesaler : {}", id);
        wholesalerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
