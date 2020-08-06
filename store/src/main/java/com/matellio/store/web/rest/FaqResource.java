package com.matellio.store.web.rest;

import com.matellio.store.service.FaqService;
import com.matellio.store.web.rest.errors.BadRequestAlertException;
import com.matellio.store.service.dto.FaqDTO;

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
 * REST controller for managing {@link com.matellio.store.domain.Faq}.
 */
@RestController
@RequestMapping("/api")
public class FaqResource {

    private final Logger log = LoggerFactory.getLogger(FaqResource.class);

    private static final String ENTITY_NAME = "faq";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FaqService faqService;

    public FaqResource(FaqService faqService) {
        this.faqService = faqService;
    }

    /**
     * {@code POST  /faqs} : Create a new faq.
     *
     * @param faqDTO the faqDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new faqDTO, or with status {@code 400 (Bad Request)} if the faq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/faqs")
    public ResponseEntity<FaqDTO> createFaq(@RequestBody FaqDTO faqDTO) throws URISyntaxException {
        log.debug("REST request to save Faq : {}", faqDTO);
        if (faqDTO.getId() != null) {
            throw new BadRequestAlertException("A new faq cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FaqDTO result = faqService.save(faqDTO);
        return ResponseEntity.created(new URI("/api/faqs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /faqs} : Updates an existing faq.
     *
     * @param faqDTO the faqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated faqDTO,
     * or with status {@code 400 (Bad Request)} if the faqDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the faqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/faqs")
    public ResponseEntity<FaqDTO> updateFaq(@RequestBody FaqDTO faqDTO) throws URISyntaxException {
        log.debug("REST request to update Faq : {}", faqDTO);
        if (faqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FaqDTO result = faqService.save(faqDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, faqDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /faqs} : get all the faqs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of faqs in body.
     */
    @GetMapping("/faqs")
    public ResponseEntity<List<FaqDTO>> getAllFaqs(Pageable pageable) {
        log.debug("REST request to get a page of Faqs");
        Page<FaqDTO> page = faqService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /faqs/:id} : get the "id" faq.
     *
     * @param id the id of the faqDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the faqDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/faqs/{id}")
    public ResponseEntity<FaqDTO> getFaq(@PathVariable String id) {
        log.debug("REST request to get Faq : {}", id);
        Optional<FaqDTO> faqDTO = faqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(faqDTO);
    }

    /**
     * {@code DELETE  /faqs/:id} : delete the "id" faq.
     *
     * @param id the id of the faqDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/faqs/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable String id) {
        log.debug("REST request to delete Faq : {}", id);
        faqService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
