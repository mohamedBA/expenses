package com.pfe.expense.service.mapper;

import com.pfe.expense.domain.*;
import com.pfe.expense.service.dto.DOCFraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DOCFrais and its DTO DOCFraisDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DOCFraisMapper extends EntityMapper<DOCFraisDTO, DOCFrais> {



    default DOCFrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        DOCFrais dOCFrais = new DOCFrais();
        dOCFrais.setId(id);
        return dOCFrais;
    }
}
