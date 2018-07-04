package com.pfe.expense.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pfe.expense.service.DepenseService;
import com.pfe.expense.web.rest.errors.BadRequestAlertException;
import com.pfe.expense.web.rest.util.HeaderUtil;
import com.pfe.expense.web.rest.util.PaginationUtil;
import com.pfe.expense.service.dto.DepenseDTO;
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
 * REST controller for managing Depense.
 */
@RestController
@RequestMapping("/api")
public class DepenseResource {

    private final Logger log = LoggerFactory.getLogger(DepenseResource.class);

    private static final String ENTITY_NAME = "depense";

    private final DepenseService depenseService;

    public DepenseResource(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    /**
     * POST  /depenses : Create a new depense.
     *
     * @param depenseDTO the depenseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new depenseDTO, or with status 400 (Bad Request) if the depense has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/depenses")
    @Timed
    public ResponseEntity<DepenseDTO> createDepense(@RequestBody DepenseDTO depenseDTO) throws URISyntaxException {
        log.debug("REST request to save Depense : {}", depenseDTO);
        if (depenseDTO.getId() != null) {
            throw new BadRequestAlertException("A new depense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepenseDTO result = depenseService.save(depenseDTO);
        return ResponseEntity.created(new URI("/api/depenses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /depenses : Updates an existing depense.
     *
     * @param depenseDTO the depenseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated depenseDTO,
     * or with status 400 (Bad Request) if the depenseDTO is not valid,
     * or with status 500 (Internal Server Error) if the depenseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/depenses")
    @Timed
    public ResponseEntity<DepenseDTO> updateDepense(@RequestBody DepenseDTO depenseDTO) throws URISyntaxException {
        log.debug("REST request to update Depense : {}", depenseDTO);
        if (depenseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepenseDTO result = depenseService.save(depenseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, depenseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /depenses : get all the depenses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of depenses in body
     */
    @GetMapping("/depenses")
    @Timed
    public ResponseEntity<List<DepenseDTO>> getAllDepenses(Pageable pageable) {
        log.debug("REST request to get a page of Depenses");
        Page<DepenseDTO> page = depenseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/depenses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /depenses/:id : get the "id" depense.
     *
     * @param id the id of the depenseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the depenseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/depenses/{id}")
    @Timed
    public ResponseEntity<DepenseDTO> getDepense(@PathVariable Long id) {
        log.debug("REST request to get Depense : {}", id);
        Optional<DepenseDTO> depenseDTO = depenseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depenseDTO);
    }

    /**
     * DELETE  /depenses/:id : delete the "id" depense.
     *
     * @param id the id of the depenseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/depenses/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepense(@PathVariable Long id) {
        log.debug("REST request to delete Depense : {}", id);
        depenseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
