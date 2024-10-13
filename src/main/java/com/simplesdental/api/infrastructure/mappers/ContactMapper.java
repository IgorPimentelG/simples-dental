package com.simplesdental.api.infrastructure.mappers;

import com.simplesdental.api.domain.entities.contact.Contact;
import com.simplesdental.api.domain.entities.contact.dtos.*;
import com.simplesdental.api.domain.enums.Specialization;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact map(CreateContactInputDto input);
    void update(UpdateContactInputDto input, @MappingTarget Contact contact);

    @Mapping(target = "profissional.cargo", expression = "java(getSpecializationDescription(contact.getProfissional().getCargo()))")
    FindContactOutputDto map(Contact contact);

    @Mapping(target = "profissional.cargo", expression = "java(getSpecializationDescription(contact.getProfissional().getCargo()))")
    List<ListContactOutputDto> map(List<Contact> contacts);
    ListContactOutputDto mapSingle(Contact contact);

    default String getSpecializationDescription(Specialization specialization) {
        return specialization.getDescription();
    }
}
