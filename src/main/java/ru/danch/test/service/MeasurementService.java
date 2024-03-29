package ru.danch.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.danch.test.dto.MeasurementDto;
import ru.danch.test.dto.MeasurementMapper;
import ru.danch.test.model.MeasurementEntity;
import ru.danch.test.repository.MeasurementRepository;
import ru.danch.test.service.helper.MeasurementHelper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;
    private final MeasurementHelper measurementHelper;

    public MeasurementService(MeasurementRepository measurementRepository, MeasurementMapper measurementMapper, MeasurementHelper measurementHelper) {
        this.measurementRepository = measurementRepository;
        this.measurementMapper = measurementMapper;
        this.measurementHelper = measurementHelper;
    }

    public List<MeasurementDto> getHistoryOfUser(Long userId) {
        return StreamSupport.stream(measurementRepository.findByUserId(userId).spliterator(), false).map(measurementMapper::toDto).collect(Collectors.toList());
    }

    public MeasurementDto save(MeasurementDto measurementDto) {
        measurementHelper.validateRequest(measurementDto);

        MeasurementEntity toSave = measurementMapper.toEntity(measurementDto);
/*
        if (Objects.nonNull(toSave.getId())) {
            var existed = measurementRepository.findById(toSave.getId());
            if (existed.isPresent()) {
                toSave = measurementMapper.enrichEntity(existed.get(), measurementDto);
            }
        }
*/
        return measurementMapper.toDto(measurementRepository.save(toSave));
    }
}
