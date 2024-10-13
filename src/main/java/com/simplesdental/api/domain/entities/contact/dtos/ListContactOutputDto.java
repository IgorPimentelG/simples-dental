package com.simplesdental.api.domain.entities.contact.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListContactOutputDto {
    private UUID id;
    private String nome;
    private String contato;
    private ProfessionalOutputDto profissional;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime updatedDate;
}

