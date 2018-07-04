package com.pfe.expense.repository;

import com.pfe.expense.domain.GroupEmployeFrais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GroupEmployeFrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupEmployeFraisRepository extends JpaRepository<GroupEmployeFrais, Long> {

}
