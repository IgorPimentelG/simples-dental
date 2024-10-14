package com.simplesdental.api.infrastructure.controllers;

import com.simplesdental.api.domain.entities.contact.ContactService;
import com.simplesdental.api.domain.entities.contact.dtos.*;
import com.simplesdental.api.domain.shared.ResponseModel;
import com.simplesdental.api.domain.shared.ResponsePageModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Tag(name = "Contatos")
@RestController
@RequestMapping("/api/v1/contatos")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    @Operation(summary = "Criar contato")
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody CreateContactInputDto input) {
        var result = contactService.create(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contato")
    public ResponseEntity<ResponseModel> update(
      @PathVariable UUID id,
      @Valid @RequestBody UpdateContactInputDto input
    ) {
        var result = contactService.update(id, input);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir contato")
    public ResponseEntity<ResponseModel> delete(@PathVariable UUID id) {
        var result = contactService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(summary = "Listar contatos")
    public ResponseEntity<ResponsePageModel<ListContactOutputDto>> getAll(
      @RequestParam String q,
      @RequestParam(required = false) List<String> fields,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var result = contactService.findAll(q, fields, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contato por ID")
    public ResponseEntity<FindContactOutputDto> getById(@PathVariable UUID id) {
        var result = contactService.find(id);
        return ResponseEntity.ok(result);
    }
}
