package com.simplesdental.api.infrastructure.repositories;

import com.simplesdental.api.domain.entities.contact.Contact;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ContactRepository extends JpaRepository<Contact, UUID>, JpaSpecificationExecutor<Contact> {

    @Query("SELECT c FROM Contact c WHERE c.id = :id AND c.deletedDate IS NULL")
    Optional<Contact> findById(@Param("id") UUID id);
}
