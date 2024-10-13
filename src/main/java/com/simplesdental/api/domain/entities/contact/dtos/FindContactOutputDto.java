package com.simplesdental.api.domain.entities.contact.dtos;

import java.util.UUID;

public record FindContactOutputDto(
  UUID id,
  String nome,
  String contato,
  ProfessionalOutputDto profissional
) {}
