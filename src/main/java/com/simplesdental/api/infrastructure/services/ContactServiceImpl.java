package com.simplesdental.api.infrastructure.services;

import com.simplesdental.api.domain.entities.contact.*;
import com.simplesdental.api.domain.entities.contact.dtos.*;
import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.shared.ResponseModel;
import com.simplesdental.api.infrastructure.errors.NotFoundException;
import com.simplesdental.api.infrastructure.mappers.ContactMapper;
import com.simplesdental.api.infrastructure.repositories.*;
import com.simplesdental.api.infrastructure.repositories.specifications.ContactSpecification;
import com.simplesdental.api.infrastructure.utils.JsonFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ProfessionalRepository professionalRepository;
    private final ContactMapper contactMapper = ContactMapper.INSTANCE;

    @Override
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
    public List<ListContactOutputDto> findAll(String search, List<String> fields) {
        var specification = ContactSpecification.build(search);
        var contacts = contactRepository.findAll(specification);
        var contactsOutput = contactMapper.map(contacts);
        return JsonFormatter.onlyFields(contactsOutput, fields);
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
