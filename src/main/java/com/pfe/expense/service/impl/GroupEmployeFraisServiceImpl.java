package com.pfe.expense.service.impl;

import com.pfe.expense.service.GroupEmployeFraisService;
import com.pfe.expense.domain.GroupEmployeFrais;
import com.pfe.expense.repository.GroupEmployeFraisRepository;
import com.pfe.expense.service.dto.GroupEmployeFraisDTO;
import com.pfe.expense.service.mapper.GroupEmployeFraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing GroupEmployeFrais.
 */
@Service
@Transactional
public class GroupEmployeFraisServiceImpl implements GroupEmployeFraisService {

    private final Logger log = LoggerFactory.getLogger(GroupEmployeFraisServiceImpl.class);

    private final GroupEmployeFraisRepository groupEmployeFraisRepository;

    private final GroupEmployeFraisMapper groupEmployeFraisMapper;

    public GroupEmployeFraisServiceImpl(GroupEmployeFraisRepository groupEmployeFraisRepository, GroupEmployeFraisMapper groupEmployeFraisMapper) {
        this.groupEmployeFraisRepository = groupEmployeFraisRepository;
        this.groupEmployeFraisMapper = groupEmployeFraisMapper;
    }

    /**
     * Save a groupEmployeFrais.
     *
     * @param groupEmployeFraisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GroupEmployeFraisDTO save(GroupEmployeFraisDTO groupEmployeFraisDTO) {
        log.debug("Request to save GroupEmployeFrais : {}", groupEmployeFraisDTO);
        GroupEmployeFrais groupEmployeFrais = groupEmployeFraisMapper.toEntity(groupEmployeFraisDTO);
        groupEmployeFrais = groupEmployeFraisRepository.save(groupEmployeFrais);
        return groupEmployeFraisMapper.toDto(groupEmployeFrais);
    }

    /**
     * Get all the groupEmployeFrais.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<GroupEmployeFraisDTO> findAll() {
        log.debug("Request to get all GroupEmployeFrais");
        return groupEmployeFraisRepository.findAll().stream()
            .map(groupEmployeFraisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one groupEmployeFrais by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupEmployeFraisDTO> findOne(Long id) {
        log.debug("Request to get GroupEmployeFrais : {}", id);
        return groupEmployeFraisRepository.findById(id)
            .map(groupEmployeFraisMapper::toDto);
    }

    /**
     * Delete the groupEmployeFrais by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GroupEmployeFrais : {}", id);
        groupEmployeFraisRepository.deleteById(id);
    }
}
