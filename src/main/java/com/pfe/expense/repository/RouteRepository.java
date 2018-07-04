package com.pfe.expense.repository;

import com.pfe.expense.domain.Route;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Route entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
