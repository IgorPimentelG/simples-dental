package com.simplesdental.api.infrastructure.controllers;

import com.simplesdental.api.domain.entities.professional.ProfessionalService;
import com.simplesdental.api.domain.entities.professional.dtos.*;
import com.simplesdental.api.domain.shared.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Tag(name = "Profissional")
@RestController
@RequestMapping("/api/v1/profissionais")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @PostMapping
    @Operation(summary = "Criar profissional")
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody CreateProfessionalInputDto input) {
        var result = professionalService.create(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar profissional")
    public ResponseEntity<ResponseModel> update(
      @PathVariable UUID id,
      @Valid @RequestBody UpdateProfessionalInputDto input
    ) {
        var result = professionalService.update(id, input);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir profissional")
    public ResponseEntity<ResponseModel> delete(@PathVariable UUID id) {
        var result = professionalService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(summary = "Listar profissionais")
    public ResponseEntity<List<ListProfessionalOutputDto>> getAll(
      @RequestParam String q,
      @RequestParam(required = false) List<String> fields
    ) {
        var result = professionalService.findAll(q, fields);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID")
    public ResponseEntity<FindProfessionalOutputDto> getById(@PathVariable UUID id) {
        var result = professionalService.find(id);
        return ResponseEntity.ok(result);
    }
}
