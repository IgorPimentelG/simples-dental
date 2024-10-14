package com.simplesdental.api.infrastructure.services;

import com.simplesdental.api.domain.entities.contact.Contact;
import com.simplesdental.api.domain.entities.contact.dtos.*;
import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.infrastructure.errors.NotFoundException;
import com.simplesdental.api.infrastructure.mocks.*;
import com.simplesdental.api.infrastructure.repositories.*;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@DisplayName("Contact Unit Tests")
public class ContactTest {

    @Mock
    ContactRepository contactRepository;

    @Mock
    ProfessionalRepository professionalRepository;

    @InjectMocks
    ContactServiceImpl contactService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should create a contact")
    public void testCreateContact() {
        Professional professional = MockProfessional.createEntity();
        when(professionalRepository.findById(any())).thenReturn(Optional.of(professional));

        CreateContactInputDto input = MockContact.createContactInput();
        Contact contact = MockContact.createEntity();
        when(contactRepository.save(any())).thenReturn(contact);

        var output = contactService.create(input);

        verify(professionalRepository, times(1)).findById(any());
        verify(contactRepository, times(1)).save(any());
        assertNotNull(output.message());
        assertEquals(output.message(), "Sucesso contato com id " + contact.getId() + " cadastrado");
        assertEquals(professional.getContatos().size(), 1);
    }

    @Test
    @DisplayName("should update contact")
    public void testUpdateContact() {
        UpdateContactInputDto input = MockContact.updateContactInput();
        Contact contact = MockContact.createEntity();
        when(contactRepository.findById(any())).thenReturn(Optional.of(contact));

        var output1 = contactService.update(contact.getId(), input);

        verify(contactRepository, times(1)).save(any());
        verify(contactRepository, times(1)).findById(any());
        assertNotNull(output1.message());
        assertEquals(output1.message(), "Sucesso cadastro alterado");
        assertEquals(contact.getNome(), input.nome());
        assertEquals(contact.getContato(), input.contato());

        Professional professional = MockProfessional.createEntity();
        when(professionalRepository.findById(any())).thenReturn(Optional.of(professional));

        var lastProfessional = contact.getProfissional();

        var output2 = contactService.update(contact.getId(), new UpdateContactInputDto(
          "New name", "New contact", professional.getId()
        ));

        verify(professionalRepository, times(1)).findById(any());
        assertNotNull(output2.message());
        assertEquals(output2.message(), "Sucesso cadastro alterado");
        assertEquals(professional.getContatos().size(), 1);
        assertEquals(lastProfessional.getContatos().size(), 0);
    }

    @Test
    @DisplayName("should throw an error when update contact doest not exist")
    public void testUpdateContactDoesNotExist() {
        UUID contactId = UUID.randomUUID();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            contactService.update(contactId, MockContact.updateContactInput());
        });

        String expectedMessage = "Contato com ID " + contactId + " não foi encontrado.";
        String outputMessage = exception.getMessage();

        verify(contactRepository, times(1)).findById(any());
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    @DisplayName("should delete contact")
    public void testDeleteContact() {
        Contact contact = MockContact.createEntity();
        when(contactRepository.findById(any())).thenReturn(Optional.of(contact));

        assertNull(contact.getDeletedDate());

        var output = contactService.delete(contact.getId());

        verify(contactRepository, times(1)).findById(any());
        assertNotNull(contact.getDeletedDate());
        assertNotNull(output.message());
        assertEquals(output.message(), "Sucesso contato excluído");
        assertEquals(contact.getProfissional().getContatos().size(), 0);
    }

    @Test
    @DisplayName("should throw an error when delete contact doest not exist")
    public void testDeleteProfessionalDoesNotExist() {
        UUID contactId = UUID.randomUUID();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            contactService.delete(contactId);
        });

        String expectedMessage = "Contato com ID " + contactId + " não foi encontrado.";
        String outputMessage = exception.getMessage();

        verify(contactRepository, times(1)).findById(any());
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    @DisplayName("should find contact")
    public void testFindContact() {
        Contact contact = MockContact.createEntity();
        when(contactRepository.findById(any())).thenReturn(Optional.of(contact));

        var output = contactService.find(contact.getId());

        verify(contactRepository, times(1)).findById(any());
        assertEquals(output.id(), contact.getId());
        assertEquals(output.nome(), contact.getNome());
        assertEquals(output.contato(), contact.getContato());
        assertNotNull(output.profissional());
    }

    @Test
    @DisplayName("should throw an error when find contact doest not exist")
    public void testFindContactDoesNotExist() {
        UUID contactId = UUID.randomUUID();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            contactService.find(contactId);
        });

        String expectedMessage = "Contato com ID " + contactId + " não foi encontrado.";
        String outputMessage = exception.getMessage();

        verify(contactRepository, times(1)).findById(any());
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    @DisplayName("should list contacts")
    public void testListContacts() {
        List<Contact> contacts = MockContact.createListEntities();
        when(contactRepository.findAll(any(Specification.class))).thenReturn(contacts);

        var output = contactService.findAll("Any", null);

        verify(contactRepository, times(1)).findAll(any(Specification.class));
        assertEquals(contacts.size(), output.size());
    }
}
