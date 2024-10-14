package com.simplesdental.api.domain.entities.professional;

import com.simplesdental.api.domain.entities.professional.dtos.*;
import com.simplesdental.api.domain.shared.*;

import java.util.List;
import java.util.UUID;

public interface ProfessionalService {
    ResponseModel create(CreateProfessionalInputDto input);
    ResponseModel update(UUID id, UpdateProfessionalInputDto input);
    ResponseModel delete(UUID id);
    FindProfessionalOutputDto find(UUID id);
    ResponsePageModel<ListProfessionalOutputDto> findAll(String search, List<String> fields, int page, int size);
}
