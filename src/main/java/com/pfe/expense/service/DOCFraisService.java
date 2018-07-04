package com.pfe.expense.service;

import com.pfe.expense.service.dto.DOCFraisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DOCFrais.
 */
public interface DOCFraisService {

    /**
     * Save a dOCFrais.
     *
     * @param dOCFraisDTO the entity to save
     * @return the persisted entity
     */
    DOCFraisDTO save(DOCFraisDTO dOCFraisDTO);

    /**
     * Get all the dOCFrais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DOCFraisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dOCFrais.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DOCFraisDTO> findOne(Long id);

    /**
     * Delete the "id" dOCFrais.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
