package com.pfe.expense.service.impl;

import com.pfe.expense.service.EmployeFraisService;
import com.pfe.expense.domain.EmployeFrais;
import com.pfe.expense.repository.EmployeFraisRepository;
import com.pfe.expense.service.dto.EmployeFraisDTO;
import com.pfe.expense.service.mapper.EmployeFraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing EmployeFrais.
 */
@Service
@Transactional
public class EmployeFraisServiceImpl implements EmployeFraisService {

    private final Logger log = LoggerFactory.getLogger(EmployeFraisServiceImpl.class);

    private final EmployeFraisRepository employeFraisRepository;

    private final EmployeFraisMapper employeFraisMapper;

    public EmployeFraisServiceImpl(EmployeFraisRepository employeFraisRepository, EmployeFraisMapper employeFraisMapper) {
        this.employeFraisRepository = employeFraisRepository;
        this.employeFraisMapper = employeFraisMapper;
    }

    /**
     * Save a employeFrais.
     *
     * @param employeFraisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmployeFraisDTO save(EmployeFraisDTO employeFraisDTO) {
        log.debug("Request to save EmployeFrais : {}", employeFraisDTO);
        EmployeFrais employeFrais = employeFraisMapper.toEntity(employeFraisDTO);
        employeFrais = employeFraisRepository.save(employeFrais);
        return employeFraisMapper.toDto(employeFrais);
    }

    /**
     * Get all the employeFrais.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeFraisDTO> findAll() {
        log.debug("Request to get all EmployeFrais");
        return employeFraisRepository.findAll().stream()
            .map(employeFraisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one employeFrais by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeFraisDTO> findOne(Long id) {
        log.debug("Request to get EmployeFrais : {}", id);
        return employeFraisRepository.findById(id)
            .map(employeFraisMapper::toDto);
    }

    /**
     * Delete the employeFrais by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmployeFrais : {}", id);
        employeFraisRepository.deleteById(id);
    }
}
