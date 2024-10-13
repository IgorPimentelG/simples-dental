package com.simplesdental.api.domain.entities.professional.dtos;

import java.time.LocalDate;
import java.util.*;

public record FindProfessionalOutputDto(
  UUID id,
  String nome,
  LocalDate nascimento,
  String cargo,
  List<ContactOutputDto> contatos
) {}