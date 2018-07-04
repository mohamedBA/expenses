package com.pfe.expense.service.mapper;

import com.pfe.expense.domain.*;
import com.pfe.expense.service.dto.DepenseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Depense and its DTO DepenseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepenseMapper extends EntityMapper<DepenseDTO, Depense> {



    default Depense fromId(Long id) {
        if (id == null) {
            return null;
        }
        Depense depense = new Depense();
        depense.setId(id);
        return depense;
    }
}
