package com.pfe.expense.repository;

import com.pfe.expense.domain.DOCFrais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DOCFrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DOCFraisRepository extends JpaRepository<DOCFrais, Long> {

}
