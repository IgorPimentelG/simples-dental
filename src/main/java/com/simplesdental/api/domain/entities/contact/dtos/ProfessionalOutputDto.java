package com.simplesdental.api.domain.entities.contact.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record ProfessionalOutputDto(
  UUID id,
  String nome,
  LocalDate nascimento,
  String cargo
) {}
