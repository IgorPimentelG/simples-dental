package com.simplesdental.api.infrastructure.mappers;

import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.entities.professional.dtos.*;
import com.simplesdental.api.domain.enums.Specialization;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProfessionalMapper {
    Professional map(CreateProfessionalInputDto input);
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
