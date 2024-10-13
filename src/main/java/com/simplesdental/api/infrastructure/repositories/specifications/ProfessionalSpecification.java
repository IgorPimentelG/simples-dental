package com.simplesdental.api.infrastructure.repositories.specifications;

import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.infrastructure.utils.QueryFormatter;
import org.springframework.data.jpa.domain.Specification;

public abstract class ProfessionalSpecification {

    public static Specification<Professional> build(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
            criteriaBuilder.isNull(root.get("deletedDate")),
            criteriaBuilder.or(
                QueryFormatter.unaccentField(criteriaBuilder, root, search, "nome"),
                QueryFormatter.unaccentField(criteriaBuilder, root, search, "cargo"),

                criteriaBuilder.or(
                  QueryFormatter.dateToCharField(criteriaBuilder, root, search, "nascimento", "DD/MM/YYYY"),
                  QueryFormatter.dateToCharField(criteriaBuilder, root, search, "nascimento", "YYYY-MM-DD")
                )
            )
        );
    }
}