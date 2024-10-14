package com.simplesdental.api.infrastructure.mocks;

import com.simplesdental.api.domain.entities.contact.Contact;
import com.simplesdental.api.domain.entities.contact.dtos.*;

import java.util.*;

public abstract class MockContact {

    public static Contact createEntity() {
        var professional = MockProfessional.createEntity();
        var contact = new Contact("Any name", "Any contact", professional);
        contact.setId(UUID.randomUUID());
        return contact;
    }

    public static CreateContactInputDto createContactInput() {
        return new CreateContactInputDto("Any name", "Any contact", UUID.randomUUID());
    }

    public static UpdateContactInputDto updateContactInput() {
        return new UpdateContactInputDto("New name", "New contact", null);
    }

    public static List<Contact> createListEntities() {
        return List.of(createEntity(), createEntity(), createEntity());
    }
}
