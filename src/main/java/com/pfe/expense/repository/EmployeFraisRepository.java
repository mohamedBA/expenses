package com.pfe.expense.repository;

import com.pfe.expense.domain.EmployeFrais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EmployeFrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeFraisRepository extends JpaRepository<EmployeFrais, Long> {

}
