package com.simplesdental.api.infrastructure.utils;

import jakarta.persistence.criteria.*;
import java.text.Normalizer;

public abstract class QueryFormatter {

    public static Predicate unaccentField(CriteriaBuilder criteriaBuilder, Root<?> root, String search, String field) {
        return criteriaBuilder.like(
          criteriaBuilder.lower(criteriaBuilder.function("unaccent", String.class, root.get(field))),
          like(search)
        );
    }

    public static Predicate dateToCharField(
      CriteriaBuilder criteriaBuilder,
      Root<?> root,
      String search,
      String field,
      String format
    ) {
        return criteriaBuilder.like(
          criteriaBuilder.function("to_char", String.class, root.get(field), criteriaBuilder.literal(format)),
          like(search)
        );
    }

    public static String removeAccents(String value) {
        return Normalizer.normalize(value, Normalizer.Form.NFD)
          .replaceAll("[^\\p{ASCII}]", "");
    }

    public static String like(String value) {
        return "%" + removeAccents(value.toLowerCase()) + "%";
    }
}
