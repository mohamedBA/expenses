package com.pfe.expense.service.impl;

import com.pfe.expense.service.DOCFraisService;
import com.pfe.expense.domain.DOCFrais;
import com.pfe.expense.repository.DOCFraisRepository;
import com.pfe.expense.service.dto.DOCFraisDTO;
import com.pfe.expense.service.mapper.DOCFraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing DOCFrais.
 */
@Service
@Transactional
public class DOCFraisServiceImpl implements DOCFraisService {

    private final Logger log = LoggerFactory.getLogger(DOCFraisServiceImpl.class);

    private final DOCFraisRepository dOCFraisRepository;

    private final DOCFraisMapper dOCFraisMapper;

    public DOCFraisServiceImpl(DOCFraisRepository dOCFraisRepository, DOCFraisMapper dOCFraisMapper) {
        this.dOCFraisRepository = dOCFraisRepository;
        this.dOCFraisMapper = dOCFraisMapper;
    }

    /**
     * Save a dOCFrais.
     *
     * @param dOCFraisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DOCFraisDTO save(DOCFraisDTO dOCFraisDTO) {
        log.debug("Request to save DOCFrais : {}", dOCFraisDTO);
        DOCFrais dOCFrais = dOCFraisMapper.toEntity(dOCFraisDTO);
        dOCFrais = dOCFraisRepository.save(dOCFrais);
        return dOCFraisMapper.toDto(dOCFrais);
    }

    /**
     * Get all the dOCFrais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DOCFraisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DOCFrais");
        return dOCFraisRepository.findAll(pageable)
            .map(dOCFraisMapper::toDto);
    }


    /**
     * Get one dOCFrais by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DOCFraisDTO> findOne(Long id) {
        log.debug("Request to get DOCFrais : {}", id);
        return dOCFraisRepository.findById(id)
            .map(dOCFraisMapper::toDto);
    }

    /**
     * Delete the dOCFrais by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DOCFrais : {}", id);
        dOCFraisRepository.deleteById(id);
    }
}
