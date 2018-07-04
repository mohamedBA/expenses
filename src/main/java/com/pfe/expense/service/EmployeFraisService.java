package com.pfe.expense.service;

import com.pfe.expense.service.dto.EmployeFraisDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EmployeFrais.
 */
public interface EmployeFraisService {

    /**
     * Save a employeFrais.
     *
     * @param employeFraisDTO the entity to save
     * @return the persisted entity
     */
    EmployeFraisDTO save(EmployeFraisDTO employeFraisDTO);

    /**
     * Get all the employeFrais.
     *
     * @return the list of entities
     */
    List<EmployeFraisDTO> findAll();


    /**
     * Get the "id" employeFrais.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EmployeFraisDTO> findOne(Long id);

    /**
     * Delete the "id" employeFrais.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
