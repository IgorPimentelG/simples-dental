package com.simplesdental.api.infrastructure.mappers;

import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.entities.professional.dtos.*;
import com.simplesdental.api.domain.enums.Specialization;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProfessionalMapper {

    ProfessionalMapper INSTANCE = Mappers.getMapper(ProfessionalMapper.class);

    Professional map(CreateProfessionalInputDto input);
    ListProfessionalOutputDto mapSingle(Professional professional);
    void update(UpdateProfessionalInputDto input, @MappingTarget Professional professional);

    @Mapping(target = "cargo", expression = "java(getSpecializationDescription(professional.getCargo()))")
    FindProfessionalOutputDto map(Professional professional);

    @Mapping(target = "cargo", expression = "java(getSpecializationDescription(professional.getCargo()))")
    List<ListProfessionalOutputDto> map(List<Professional> professionals);

    default String getSpecializationDescription(Specialization specialization) {
        if (specialization != null) {
            return specialization.getDescription();
        }
        return null;
    }
}
