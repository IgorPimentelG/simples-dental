package com.simplesdental.api.domain.entities.professional.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListProfessionalOutputDto {
    private UUID id;
    private String nome;
    private String cargo;
    private LocalDate nascimento;
    private List<ContactOutputDto> contatos;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime updatedDate;
}
