package com.simplesdental.api.infrastructure.repositories;

import com.simplesdental.api.domain.entities.professional.Professional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID>, JpaSpecificationExecutor<Professional> {

    @Query("SELECT p FROM Professional p WHERE p.id = :id AND p.deletedDate IS NULL")
    Optional<Professional> findById(@Param("id") UUID id);
}
