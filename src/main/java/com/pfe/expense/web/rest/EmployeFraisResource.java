package com.pfe.expense.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pfe.expense.service.EmployeFraisService;
import com.pfe.expense.web.rest.errors.BadRequestAlertException;
import com.pfe.expense.web.rest.util.HeaderUtil;
import com.pfe.expense.service.dto.EmployeFraisDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EmployeFrais.
 */
@RestController
@RequestMapping("/api")
public class EmployeFraisResource {

    private final Logger log = LoggerFactory.getLogger(EmployeFraisResource.class);

    private static final String ENTITY_NAME = "employeFrais";

    private final EmployeFraisService employeFraisService;

    public EmployeFraisResource(EmployeFraisService employeFraisService) {
        this.employeFraisService = employeFraisService;
    }

    /**
     * POST  /employe-frais : Create a new employeFrais.
     *
     * @param employeFraisDTO the employeFraisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employeFraisDTO, or with status 400 (Bad Request) if the employeFrais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employe-frais")
    @Timed
    public ResponseEntity<EmployeFraisDTO> createEmployeFrais(@RequestBody EmployeFraisDTO employeFraisDTO) throws URISyntaxException {
        log.debug("REST request to save EmployeFrais : {}", employeFraisDTO);
        if (employeFraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new employeFrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeFraisDTO result = employeFraisService.save(employeFraisDTO);
        return ResponseEntity.created(new URI("/api/employe-frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /employe-frais : Updates an existing employeFrais.
     *
     * @param employeFraisDTO the employeFraisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employeFraisDTO,
     * or with status 400 (Bad Request) if the employeFraisDTO is not valid,
     * or with status 500 (Internal Server Error) if the employeFraisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employe-frais")
    @Timed
    public ResponseEntity<EmployeFraisDTO> updateEmployeFrais(@RequestBody EmployeFraisDTO employeFraisDTO) throws URISyntaxException {
        log.debug("REST request to update EmployeFrais : {}", employeFraisDTO);
        if (employeFraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeFraisDTO result = employeFraisService.save(employeFraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employeFraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /employe-frais : get all the employeFrais.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of employeFrais in body
     */
    @GetMapping("/employe-frais")
    @Timed
    public List<EmployeFraisDTO> getAllEmployeFrais() {
        log.debug("REST request to get all EmployeFrais");
        return employeFraisService.findAll();
    }

    /**
     * GET  /employe-frais/:id : get the "id" employeFrais.
     *
     * @param id the id of the employeFraisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeFraisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/employe-frais/{id}")
    @Timed
    public ResponseEntity<EmployeFraisDTO> getEmployeFrais(@PathVariable Long id) {
        log.debug("REST request to get EmployeFrais : {}", id);
        Optional<EmployeFraisDTO> employeFraisDTO = employeFraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeFraisDTO);
    }

    /**
     * DELETE  /employe-frais/:id : delete the "id" employeFrais.
     *
     * @param id the id of the employeFraisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employe-frais/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmployeFrais(@PathVariable Long id) {
        log.debug("REST request to delete EmployeFrais : {}", id);
        employeFraisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
