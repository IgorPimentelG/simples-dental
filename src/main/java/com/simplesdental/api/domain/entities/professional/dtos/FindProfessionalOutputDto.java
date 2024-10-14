package com.simplesdental.api.domain.entities.professional.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FindProfessionalOutputDto(
  UUID id,
  String nome,
  LocalDate nascimento,
  String cargo,
  List<ContactOutputDto> contatos,

  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDateTime createdDate,

  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDateTime updatedDate
) {}