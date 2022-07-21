package ru.danch.test.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.danch.test.model.MeasurementEntity;

@Mapper
public interface MeasurementMapper {

    MeasurementEntity toEntity(MeasurementDto measurementDto);

    @Mapping(source = "measurementEntity.id", target = "id")
    @Mapping(source = "measurementDto.userId", target = "userId")
    @Mapping(source = "measurementDto.type", target = "type")
    @Mapping(source = "measurementDto.usage", target = "usage")
    @Mapping(source = "measurementEntity.lastEditingDate", target = "lastEditingDate")
    MeasurementEntity enrichEntity(MeasurementEntity measurementEntity, MeasurementDto measurementDto);

    MeasurementDto toDto(MeasurementEntity measurementEntity);
}
