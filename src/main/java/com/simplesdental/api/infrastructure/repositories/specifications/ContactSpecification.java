package com.simplesdental.api.infrastructure.repositories.specifications;

import com.simplesdental.api.domain.entities.contact.Contact;
import com.simplesdental.api.infrastructure.utils.QueryFormatter;
import org.springframework.data.jpa.domain.Specification;

public abstract class ContactSpecification {

    public static Specification<Contact> build(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
          criteriaBuilder.isNull(root.get("deletedDate")),
          criteriaBuilder.or(
            QueryFormatter.unaccentField(criteriaBuilder, root, search, "nome"),
            QueryFormatter.unaccentField(criteriaBuilder, root, search, "contato")
          )
        );
    }
}
