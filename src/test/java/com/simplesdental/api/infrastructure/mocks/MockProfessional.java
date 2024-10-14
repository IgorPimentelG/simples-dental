package com.simplesdental.api.infrastructure.mocks;

import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.entities.professional.dtos.*;
import com.simplesdental.api.domain.enums.Specialization;

import java.time.LocalDate;
import java.util.*;

public abstract class MockProfessional {

    public static Professional createEntity() {
        var professional = new Professional(
          "Any name",
          LocalDate.of(2000, 2, 15),
          Specialization.DESENVOLVEDOR,
          new ArrayList<>()
        );
        professional.setId(UUID.randomUUID());
        return professional;
    }

    public static CreateProfessionalInputDto createProfessionalInput() {
        return new CreateProfessionalInputDto(
          "Any name",
          Specialization.DESENVOLVEDOR,
          LocalDate.of(2000, 2, 15)
        );
    }

    public static UpdateProfessionalInputDto updateProfessionalInput() {
        return new UpdateProfessionalInputDto(
          "New name",
          LocalDate.of(2005, 2, 15),
          Specialization.SUPORTE
        );
    }

    public static List<Professional> createListEntities() {
        return List.of(createEntity(), createEntity(), createEntity());
    }
}
