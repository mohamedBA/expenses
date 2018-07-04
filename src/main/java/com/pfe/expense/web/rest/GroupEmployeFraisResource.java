package com.pfe.expense.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pfe.expense.service.GroupEmployeFraisService;
import com.pfe.expense.web.rest.errors.BadRequestAlertException;
import com.pfe.expense.web.rest.util.HeaderUtil;
import com.pfe.expense.service.dto.GroupEmployeFraisDTO;
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
 * REST controller for managing GroupEmployeFrais.
 */
@RestController
@RequestMapping("/api")
public class GroupEmployeFraisResource {

    private final Logger log = LoggerFactory.getLogger(GroupEmployeFraisResource.class);

    private static final String ENTITY_NAME = "groupEmployeFrais";

    private final GroupEmployeFraisService groupEmployeFraisService;

    public GroupEmployeFraisResource(GroupEmployeFraisService groupEmployeFraisService) {
        this.groupEmployeFraisService = groupEmployeFraisService;
    }

    /**
     * POST  /group-employe-frais : Create a new groupEmployeFrais.
     *
     * @param groupEmployeFraisDTO the groupEmployeFraisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groupEmployeFraisDTO, or with status 400 (Bad Request) if the groupEmployeFrais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/group-employe-frais")
    @Timed
    public ResponseEntity<GroupEmployeFraisDTO> createGroupEmployeFrais(@RequestBody GroupEmployeFraisDTO groupEmployeFraisDTO) throws URISyntaxException {
        log.debug("REST request to save GroupEmployeFrais : {}", groupEmployeFraisDTO);
        if (groupEmployeFraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupEmployeFrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupEmployeFraisDTO result = groupEmployeFraisService.save(groupEmployeFraisDTO);
        return ResponseEntity.created(new URI("/api/group-employe-frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /group-employe-frais : Updates an existing groupEmployeFrais.
     *
     * @param groupEmployeFraisDTO the groupEmployeFraisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groupEmployeFraisDTO,
     * or with status 400 (Bad Request) if the groupEmployeFraisDTO is not valid,
     * or with status 500 (Internal Server Error) if the groupEmployeFraisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/group-employe-frais")
    @Timed
    public ResponseEntity<GroupEmployeFraisDTO> updateGroupEmployeFrais(@RequestBody GroupEmployeFraisDTO groupEmployeFraisDTO) throws URISyntaxException {
        log.debug("REST request to update GroupEmployeFrais : {}", groupEmployeFraisDTO);
        if (groupEmployeFraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupEmployeFraisDTO result = groupEmployeFraisService.save(groupEmployeFraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupEmployeFraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /group-employe-frais : get all the groupEmployeFrais.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of groupEmployeFrais in body
     */
    @GetMapping("/group-employe-frais")
    @Timed
    public List<GroupEmployeFraisDTO> getAllGroupEmployeFrais() {
        log.debug("REST request to get all GroupEmployeFrais");
        return groupEmployeFraisService.findAll();
    }

    /**
     * GET  /group-employe-frais/:id : get the "id" groupEmployeFrais.
     *
     * @param id the id of the groupEmployeFraisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groupEmployeFraisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/group-employe-frais/{id}")
    @Timed
    public ResponseEntity<GroupEmployeFraisDTO> getGroupEmployeFrais(@PathVariable Long id) {
        log.debug("REST request to get GroupEmployeFrais : {}", id);
        Optional<GroupEmployeFraisDTO> groupEmployeFraisDTO = groupEmployeFraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupEmployeFraisDTO);
    }

    /**
     * DELETE  /group-employe-frais/:id : delete the "id" groupEmployeFrais.
     *
     * @param id the id of the groupEmployeFraisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/group-employe-frais/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroupEmployeFrais(@PathVariable Long id) {
        log.debug("REST request to delete GroupEmployeFrais : {}", id);
        groupEmployeFraisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
