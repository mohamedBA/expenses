package com.pfe.expense.service.mapper;

import com.pfe.expense.domain.*;
import com.pfe.expense.service.dto.GroupEmployeFraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GroupEmployeFrais and its DTO GroupEmployeFraisDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupEmployeFraisMapper extends EntityMapper<GroupEmployeFraisDTO, GroupEmployeFrais> {



    default GroupEmployeFrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        GroupEmployeFrais groupEmployeFrais = new GroupEmployeFrais();
        groupEmployeFrais.setId(id);
        return groupEmployeFrais;
    }
}
