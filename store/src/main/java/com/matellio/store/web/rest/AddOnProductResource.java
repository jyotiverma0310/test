package com.matellio.store.web.rest;

import com.matellio.store.service.AddOnProductService;
import com.matellio.store.web.rest.errors.BadRequestAlertException;
import com.matellio.store.service.dto.AddOnProductDTO;

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
 * REST controller for managing {@link com.matellio.store.domain.AddOnProduct}.
 */
@RestController
@RequestMapping("/api")
public class AddOnProductResource {

    private final Logger log = LoggerFactory.getLogger(AddOnProductResource.class);

    private static final String ENTITY_NAME = "addOnProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AddOnProductService addOnProductService;

    public AddOnProductResource(AddOnProductService addOnProductService) {
        this.addOnProductService = addOnProductService;
    }

    /**
     * {@code POST  /add-on-products} : Create a new addOnProduct.
     *
     * @param addOnProductDTO the addOnProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addOnProductDTO, or with status {@code 400 (Bad Request)} if the addOnProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/add-on-products")
    public ResponseEntity<AddOnProductDTO> createAddOnProduct(@RequestBody AddOnProductDTO addOnProductDTO) throws URISyntaxException {
        log.debug("REST request to save AddOnProduct : {}", addOnProductDTO);
        if (addOnProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new addOnProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddOnProductDTO result = addOnProductService.save(addOnProductDTO);
        return ResponseEntity.created(new URI("/api/add-on-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /add-on-products} : Updates an existing addOnProduct.
     *
     * @param addOnProductDTO the addOnProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addOnProductDTO,
     * or with status {@code 400 (Bad Request)} if the addOnProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addOnProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/add-on-products")
    public ResponseEntity<AddOnProductDTO> updateAddOnProduct(@RequestBody AddOnProductDTO addOnProductDTO) throws URISyntaxException {
        log.debug("REST request to update AddOnProduct : {}", addOnProductDTO);
        if (addOnProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddOnProductDTO result = addOnProductService.save(addOnProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, addOnProductDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /add-on-products} : get all the addOnProducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addOnProducts in body.
     */
    @GetMapping("/add-on-products")
    public ResponseEntity<List<AddOnProductDTO>> getAllAddOnProducts(Pageable pageable) {
        log.debug("REST request to get a page of AddOnProducts");
        Page<AddOnProductDTO> page = addOnProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /add-on-products/:id} : get the "id" addOnProduct.
     *
     * @param id the id of the addOnProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addOnProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/add-on-products/{id}")
    public ResponseEntity<AddOnProductDTO> getAddOnProduct(@PathVariable String id) {
        log.debug("REST request to get AddOnProduct : {}", id);
        Optional<AddOnProductDTO> addOnProductDTO = addOnProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addOnProductDTO);
    }

    /**
     * {@code DELETE  /add-on-products/:id} : delete the "id" addOnProduct.
     *
     * @param id the id of the addOnProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/add-on-products/{id}")
    public ResponseEntity<Void> deleteAddOnProduct(@PathVariable String id) {
        log.debug("REST request to delete AddOnProduct : {}", id);
        addOnProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
