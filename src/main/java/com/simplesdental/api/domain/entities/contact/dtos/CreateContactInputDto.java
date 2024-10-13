package com.simplesdental.api.domain.entities.contact.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record CreateContactInputDto(
  @NotEmpty(message = "Informe o nome")
  @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
  String nome,

  @NotEmpty(message = "Informe o contato")
  @Length(min = 5, max = 255, message = "O contato deve ter entre 5 e 255 caracteres")
  String contato,

  @NotNull
  UUID profissionalId
) {}
