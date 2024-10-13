package com.simplesdental.api.infrastructure.services;

import com.simplesdental.api.domain.entities.professional.*;
import com.simplesdental.api.domain.entities.professional.dtos.*;
import com.simplesdental.api.domain.shared.ResponseModel;
import com.simplesdental.api.infrastructure.errors.NotFoundException;
import com.simplesdental.api.infrastructure.mappers.ProfessionalMapper;
import com.simplesdental.api.infrastructure.repositories.ProfessionalRepository;
import com.simplesdental.api.infrastructure.repositories.specifications.ProfessionalSpecification;
import com.simplesdental.api.infrastructure.utils.JsonFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalMapper professionalMapper;
    private final ProfessionalRepository professionalRepository;

    @Override
    public ResponseModel create(CreateProfessionalInputDto input) {
        var professional = professionalMapper.map(input);
        var entity = professionalRepository.save(professional);
        return new ResponseModel(String.format("Sucesso profissional com id %s cadastrado", entity.getId()));
    }

    @Override
    public ResponseModel update(UUID id, UpdateProfessionalInputDto input) {
        var professional = findProfessionalById(id);
        professionalMapper.update(input, professional);
        professionalRepository.save(professional);
        return new ResponseModel("Sucesso cadastro alterado");
    }

    @Override
    public ResponseModel delete(UUID id) {
        var professional = findProfessionalById(id);
        professional.markAsDeleted();
        professionalRepository.save(professional);
        return new ResponseModel("Sucesso profissional excluído");
    }

    @Override
    public FindProfessionalOutputDto find(UUID id) {
        var professional = findProfessionalById(id);
        return professionalMapper.map(professional);
    }

    @Override
    public List<ListProfessionalOutputDto> findAll(String search, List<String> fields) {
        var specification = ProfessionalSpecification.build(search);
        var professionals = professionalRepository.findAll(specification);
        var professionalsOutput = professionalMapper.map(professionals);
        return JsonFormatter.onlyFields(professionalsOutput, fields);
    }

    private Professional findProfessionalById(UUID id) {
        return professionalRepository.findById(id)
          .orElseThrow(() -> new NotFoundException(String.format("Profissional com ID %s não foi encontrado.", id)));
    }
}
