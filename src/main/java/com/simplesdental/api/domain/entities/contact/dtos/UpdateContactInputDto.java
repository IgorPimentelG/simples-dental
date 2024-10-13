package com.simplesdental.api.domain.entities.contact.dtos;

import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record UpdateContactInputDto(
  @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
  String nome,

  @Length(min = 5, max = 255, message = "O contato deve ter entre 5 e 255 caracteres")
  String contato,

  UUID profissionalId
) {}
