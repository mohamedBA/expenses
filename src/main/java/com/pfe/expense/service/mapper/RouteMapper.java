package com.pfe.expense.service.mapper;

import com.pfe.expense.domain.*;
import com.pfe.expense.service.dto.RouteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Route and its DTO RouteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RouteMapper extends EntityMapper<RouteDTO, Route> {



    default Route fromId(Long id) {
        if (id == null) {
            return null;
        }
        Route route = new Route();
        route.setId(id);
        return route;
    }
}
