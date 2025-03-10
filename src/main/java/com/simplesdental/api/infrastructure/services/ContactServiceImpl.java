package com.simplesdental.api.infrastructure.services;

import com.simplesdental.api.domain.entities.contact.*;
import com.simplesdental.api.domain.entities.contact.dtos.*;
import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.shared.ResponseModel;
import com.simplesdental.api.domain.shared.ResponsePageModel;
import com.simplesdental.api.infrastructure.errors.NotFoundException;
import com.simplesdental.api.infrastructure.mappers.ContactMapper;
import com.simplesdental.api.infrastructure.repositories.*;
import com.simplesdental.api.infrastructure.repositories.specifications.ContactSpecification;
import com.simplesdental.api.infrastructure.utils.JsonFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ProfessionalRepository professionalRepository;
    private final ContactMapper contactMapper = ContactMapper.INSTANCE;

    @Override
    @CacheEvict(value = "contacts", allEntries = true)
    public ResponseModel create(CreateContactInputDto input) {
        var contact = contactMapper.map(input);
        var professional = findProfessionalById(input.profissionalId());

        contact.setProfissional(professional);
        professional.addContact(contact);

        var entity = contactRepository.save(contact);
        professionalRepository.save(professional);
        return new ResponseModel(String.format("Sucesso contato com id %s cadastrado", entity.getId()));
    }

    @Override
    @CachePut(value = "contacts")
    public ResponseModel update(UUID id, UpdateContactInputDto input) {
        var contact = findContactById(id);
        contactMapper.update(input, contact);

        if (input.profissionalId() != null && !input.profissionalId().equals(contact.getProfissional().getId())) {
            contact.getProfissional().removeContact(contact);

            var professional = findProfessionalById(input.profissionalId());
            professional.addContact(contact);
            contact.setProfissional(professional);
            professionalRepository.save(professional);
        }

        contactRepository.save(contact);
        return new ResponseModel("Sucesso cadastro alterado");
    }

    @Override
    @CacheEvict(value = "contacts", allEntries = true)
    public ResponseModel delete(UUID id) {
        var contact = findContactById(id);
        contact.markAsDeleted();
        contactRepository.save(contact);
        return new ResponseModel("Sucesso contato excluído");
    }

    @Override
    public FindContactOutputDto find(UUID id) {
        var contact = findContactById(id);
        return contactMapper.map(contact);
    }

    @Override
    @Cacheable("contacts")
    public ResponsePageModel<ListContactOutputDto> findAll(
      String search,
      List<String> fields,
      int page,
      int size
    ) {
        var pageRequest = PageRequest.of(page, size);
        var specification = ContactSpecification.build(search);
        var totalItems = contactRepository.count(specification);
        var contacts = contactRepository.findAll(specification, pageRequest);
        var contactsOutput = contactMapper.map(contacts.getContent());
        var formattedContacts = JsonFormatter.onlyFields(contactsOutput, fields);
        return new ResponsePageModel<>(totalItems, page, contacts.getTotalPages(), formattedContacts);
    }

    private Contact findContactById(UUID id) {
        return contactRepository.findById(id)
          .orElseThrow(() -> new NotFoundException(String.format("Contato com ID %s não foi encontrado.", id)));
    }

    private Professional findProfessionalById(UUID id) {
        return professionalRepository.findById(id)
          .orElseThrow(() -> new NotFoundException(String.format("Profissional com ID %s não foi encontrado.", id)));
    }
}
