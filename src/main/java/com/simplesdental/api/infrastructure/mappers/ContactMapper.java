package com.simplesdental.api.infrastructure.mappers;

import com.simplesdental.api.domain.entities.contact.Contact;
import com.simplesdental.api.domain.entities.contact.dtos.*;
import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.enums.Specialization;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact map(CreateContactInputDto input);
    FindContactOutputDto map(Contact contact);
    ListContactOutputDto mapSingle(Contact contact);
    List<ListContactOutputDto> map(List<Contact> contacts);
    void update(UpdateContactInputDto input, @MappingTarget Contact contact);

    @Mapping(target = "cargo", expression = "java(getSpecializationDescription(professional.getCargo()))")
    ProfessionalOutputDto map(Professional professional);

    default String getSpecializationDescription(Specialization specialization) {
        return specialization.getDescription();
    }
}
