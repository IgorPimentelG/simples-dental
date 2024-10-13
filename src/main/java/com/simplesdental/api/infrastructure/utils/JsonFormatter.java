package com.simplesdental.api.infrastructure.utils;

import com.simplesdental.api.infrastructure.errors.BadRequestException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class JsonFormatter {

    public static <T> T onlyFields(T entity, List<String> fields) {
        if (fields != null && !fields.isEmpty()) {
            Class<?> clazz = entity.getClass();
            Field[] proprieties = clazz.getDeclaredFields();

            Arrays.stream(proprieties).forEach(field -> {
                try {
                    field.setAccessible(true);
                    if (!fields.contains(field.getName())) {
                        field.set(entity, null);
                    }
                } catch (Exception e) {
                    throw new BadRequestException("Não foi possível formatar o JSON.");
                }
            });
        }

        return entity;
    }

    public static <T> List<T> onlyFields(List<T> entities, List<String> fields) {
        return entities.stream()
            .map(entity -> onlyFields(entity, fields))
            .toList();
    }
}
