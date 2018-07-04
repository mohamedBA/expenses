package com.pfe.expense.service;

import com.pfe.expense.service.dto.GroupEmployeFraisDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing GroupEmployeFrais.
 */
public interface GroupEmployeFraisService {

    /**
     * Save a groupEmployeFrais.
     *
     * @param groupEmployeFraisDTO the entity to save
     * @return the persisted entity
     */
    GroupEmployeFraisDTO save(GroupEmployeFraisDTO groupEmployeFraisDTO);

    /**
     * Get all the groupEmployeFrais.
     *
     * @return the list of entities
     */
    List<GroupEmployeFraisDTO> findAll();


    /**
     * Get the "id" groupEmployeFrais.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GroupEmployeFraisDTO> findOne(Long id);

    /**
     * Delete the "id" groupEmployeFrais.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
