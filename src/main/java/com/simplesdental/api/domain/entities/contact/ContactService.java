package com.simplesdental.api.domain.entities.contact;

import com.simplesdental.api.domain.entities.contact.dtos.*;
import com.simplesdental.api.domain.shared.ResponseModel;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    ResponseModel create(CreateContactInputDto input);
    ResponseModel update(UUID id, UpdateContactInputDto input);
    ResponseModel delete(UUID id);
    FindContactOutputDto find(UUID id);
    List<ListContactOutputDto> findAll(String search, List<String> fields);
}
