package com.pfe.expense.service.mapper;

import com.pfe.expense.domain.*;
import com.pfe.expense.service.dto.EmployeFraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmployeFrais and its DTO EmployeFraisDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeFraisMapper extends EntityMapper<EmployeFraisDTO, EmployeFrais> {



    default EmployeFrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmployeFrais employeFrais = new EmployeFrais();
        employeFrais.setId(id);
        return employeFrais;
    }
}
