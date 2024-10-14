package com.simplesdental.api.infrastructure.services;

import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.entities.professional.dtos.*;
import com.simplesdental.api.infrastructure.errors.NotFoundException;
import com.simplesdental.api.infrastructure.mocks.MockProfessional;
import com.simplesdental.api.infrastructure.repositories.ProfessionalRepository;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@DisplayName("Professional Unit Tests")
public class ProfessionalTest {

    @Mock
    ProfessionalRepository professionalRepository;

    @InjectMocks
    ProfessionalServiceImpl professionalService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should create professional")
    public void testCreateProfessional() {
        CreateProfessionalInputDto input = MockProfessional.createProfessionalInput();
        Professional professional = MockProfessional.createEntity();
        when(professionalRepository.save(any())).thenReturn(professional);

        var output = professionalService.create(input);

        verify(professionalRepository, times(1)).save(any());
        assertNotNull(output.message());
        assertEquals(output.message(), "Sucesso profissional com id " + professional.getId() + " cadastrado");
    }

    @Test
    @DisplayName("should update professional")
    public void testUpdateProfessional() {
        UpdateProfessionalInputDto input = MockProfessional.updateProfessionalInput();
        Professional professional = MockProfessional.createEntity();
        when(professionalRepository.findById(any())).thenReturn(Optional.of(professional));

        var output = professionalService.update(professional.getId(), input);

        verify(professionalRepository, times(1)).save(any());
        verify(professionalRepository, times(1)).findById(any());
        assertNotNull(output.message());
        assertEquals(output.message(), "Sucesso cadastro alterado");
        assertEquals(professional.getNome(), input.nome());
        assertEquals(professional.getCargo(), input.cargo());
        assertEquals(professional.getNascimento(), input.nascimento());
    }

    @Test
    @DisplayName("should throw an error when update professional doest not exist")
    public void testUpdateProfessionalDoesNotExist() {
        UUID professionalId = UUID.randomUUID();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            professionalService.update(professionalId, MockProfessional.updateProfessionalInput());
        });

        String expectedMessage = "Profissional com ID " + professionalId + " não foi encontrado.";
        String outputMessage = exception.getMessage();

        verify(professionalRepository, times(1)).findById(any());
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    @DisplayName("should delete professional")
    public void testDeleteProfessional() {
        Professional professional = MockProfessional.createEntity();
        when(professionalRepository.findById(any())).thenReturn(Optional.of(professional));

        assertNull(professional.getDeletedDate());

        var output = professionalService.delete(professional.getId());

        verify(professionalRepository, times(1)).findById(any());
        assertNotNull(professional.getDeletedDate());
        assertNotNull(output.message());
        assertEquals(output.message(), "Sucesso profissional excluído");
    }

    @Test
    @DisplayName("should throw an error when delete professional doest not exist")
    public void testDeleteProfessionalDoesNotExist() {
        UUID professionalId = UUID.randomUUID();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            professionalService.delete(professionalId);
        });

        String expectedMessage = "Profissional com ID " + professionalId + " não foi encontrado.";
        String outputMessage = exception.getMessage();

        verify(professionalRepository, times(1)).findById(any());
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    @DisplayName("should find professional")
    public void testFindProfessional() {
        Professional professional = MockProfessional.createEntity();
        when(professionalRepository.findById(any())).thenReturn(Optional.of(professional));

        var output = professionalService.find(professional.getId());

        verify(professionalRepository, times(1)).findById(any());
        assertEquals(output.id(), professional.getId());
        assertEquals(output.nome(), professional.getNome());
        assertEquals(output.cargo(), professional.getCargo().getDescription());
    }

    @Test
    @DisplayName("should throw an error when find professional doest not exist")
    public void testFindProfessionalDoesNotExist() {
        UUID professionalId = UUID.randomUUID();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            professionalService.find(professionalId);
        });

        String expectedMessage = "Profissional com ID " + professionalId + " não foi encontrado.";
        String outputMessage = exception.getMessage();

        verify(professionalRepository, times(1)).findById(any());
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    @DisplayName("should list professionals")
    public void testListProfessional() {
        List<Professional> professionals = MockProfessional.createListEntities();
        when(professionalRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(professionals));
        when(professionalRepository.count(any(Specification.class))).thenReturn(Long.valueOf(professionals.size()));

        var output = professionalService.findAll("Any", List.of("nome"), 0, 10);

        verify(professionalRepository, times(1)).findAll(any(Specification.class), any(PageRequest.class));
        assertEquals(professionals.size(), output.totalItems());
        assertEquals(output.totalPages(), 1);
        assertNull(output.items().get(0).getCargo());
    }
}
