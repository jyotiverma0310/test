package com.matellio.store.web.rest;

import com.matellio.store.service.ContactUsService;
import com.matellio.store.web.rest.errors.BadRequestAlertException;
import com.matellio.store.service.dto.ContactUsDTO;

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
 * REST controller for managing {@link com.matellio.store.domain.ContactUs}.
 */
@RestController
@RequestMapping("/api")
public class ContactUsResource {

    private final Logger log = LoggerFactory.getLogger(ContactUsResource.class);

    private static final String ENTITY_NAME = "contactUs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactUsService contactUsService;

    public ContactUsResource(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    /**
     * {@code POST  /contactuses} : Create a new contactUs.
     *
     * @param contactUsDTO the contactUsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactUsDTO, or with status {@code 400 (Bad Request)} if the contactUs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contactuses")
    public ResponseEntity<ContactUsDTO> createContactUs(@RequestBody ContactUsDTO contactUsDTO) throws URISyntaxException {
        log.debug("REST request to save ContactUs : {}", contactUsDTO);
        if (contactUsDTO.getId() != null) {
            throw new BadRequestAlertException("A new contactUs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactUsDTO result = contactUsService.save(contactUsDTO);
        return ResponseEntity.created(new URI("/api/contactuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /contactuses} : Updates an existing contactUs.
     *
     * @param contactUsDTO the contactUsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactUsDTO,
     * or with status {@code 400 (Bad Request)} if the contactUsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactUsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contactuses")
    public ResponseEntity<ContactUsDTO> updateContactUs(@RequestBody ContactUsDTO contactUsDTO) throws URISyntaxException {
        log.debug("REST request to update ContactUs : {}", contactUsDTO);
        if (contactUsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactUsDTO result = contactUsService.save(contactUsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactUsDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /contactuses} : get all the contactuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactuses in body.
     */
    @GetMapping("/contactuses")
    public ResponseEntity<List<ContactUsDTO>> getAllContactuses(Pageable pageable) {
        log.debug("REST request to get a page of Contactuses");
        Page<ContactUsDTO> page = contactUsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contactuses/:id} : get the "id" contactUs.
     *
     * @param id the id of the contactUsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactUsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contactuses/{id}")
    public ResponseEntity<ContactUsDTO> getContactUs(@PathVariable String id) {
        log.debug("REST request to get ContactUs : {}", id);
        Optional<ContactUsDTO> contactUsDTO = contactUsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactUsDTO);
    }

    /**
     * {@code DELETE  /contactuses/:id} : delete the "id" contactUs.
     *
     * @param id the id of the contactUsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contactuses/{id}")
    public ResponseEntity<Void> deleteContactUs(@PathVariable String id) {
        log.debug("REST request to delete ContactUs : {}", id);
        contactUsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
