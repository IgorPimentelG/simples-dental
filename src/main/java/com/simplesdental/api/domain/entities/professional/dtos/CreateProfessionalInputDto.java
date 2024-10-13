package com.simplesdental.api.domain.entities.professional.dtos;

import com.simplesdental.api.domain.enums.Specialization;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateProfessionalInputDto(
  @NotEmpty(message = "Informe o nome")
  @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
  String nome,

  @NotNull(message = "Informe o cargo")
  Specialization cargo,

  @NotNull(message = "Informe a data de nascimento")
  @Past(message = "Data de nascimento não pode ser no futuro")
  LocalDate nascimento
) {}