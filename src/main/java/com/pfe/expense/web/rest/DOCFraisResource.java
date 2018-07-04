package com.pfe.expense.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pfe.expense.service.DOCFraisService;
import com.pfe.expense.web.rest.errors.BadRequestAlertException;
import com.pfe.expense.web.rest.util.HeaderUtil;
import com.pfe.expense.web.rest.util.PaginationUtil;
import com.pfe.expense.service.dto.DOCFraisDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DOCFrais.
 */
@RestController
@RequestMapping("/api")
public class DOCFraisResource {

    private final Logger log = LoggerFactory.getLogger(DOCFraisResource.class);

    private static final String ENTITY_NAME = "dOCFrais";

    private final DOCFraisService dOCFraisService;

    public DOCFraisResource(DOCFraisService dOCFraisService) {
        this.dOCFraisService = dOCFraisService;
    }

    /**
     * POST  /doc-frais : Create a new dOCFrais.
     *
     * @param dOCFraisDTO the dOCFraisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dOCFraisDTO, or with status 400 (Bad Request) if the dOCFrais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/doc-frais")
    @Timed
    public ResponseEntity<DOCFraisDTO> createDOCFrais(@RequestBody DOCFraisDTO dOCFraisDTO) throws URISyntaxException {
        log.debug("REST request to save DOCFrais : {}", dOCFraisDTO);
        if (dOCFraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new dOCFrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DOCFraisDTO result = dOCFraisService.save(dOCFraisDTO);
        return ResponseEntity.created(new URI("/api/doc-frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /doc-frais : Updates an existing dOCFrais.
     *
     * @param dOCFraisDTO the dOCFraisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dOCFraisDTO,
     * or with status 400 (Bad Request) if the dOCFraisDTO is not valid,
     * or with status 500 (Internal Server Error) if the dOCFraisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/doc-frais")
    @Timed
    public ResponseEntity<DOCFraisDTO> updateDOCFrais(@RequestBody DOCFraisDTO dOCFraisDTO) throws URISyntaxException {
        log.debug("REST request to update DOCFrais : {}", dOCFraisDTO);
        if (dOCFraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DOCFraisDTO result = dOCFraisService.save(dOCFraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dOCFraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /doc-frais : get all the dOCFrais.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dOCFrais in body
     */
    @GetMapping("/doc-frais")
    @Timed
    public ResponseEntity<List<DOCFraisDTO>> getAllDOCFrais(Pageable pageable) {
        log.debug("REST request to get a page of DOCFrais");
        Page<DOCFraisDTO> page = dOCFraisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/doc-frais");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /doc-frais/:id : get the "id" dOCFrais.
     *
     * @param id the id of the dOCFraisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dOCFraisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/doc-frais/{id}")
    @Timed
    public ResponseEntity<DOCFraisDTO> getDOCFrais(@PathVariable Long id) {
        log.debug("REST request to get DOCFrais : {}", id);
        Optional<DOCFraisDTO> dOCFraisDTO = dOCFraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dOCFraisDTO);
    }

    /**
     * DELETE  /doc-frais/:id : delete the "id" dOCFrais.
     *
     * @param id the id of the dOCFraisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/doc-frais/{id}")
    @Timed
    public ResponseEntity<Void> deleteDOCFrais(@PathVariable Long id) {
        log.debug("REST request to delete DOCFrais : {}", id);
        dOCFraisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
