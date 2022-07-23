package ru.danch.test.service.helper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.danch.test.dto.MeasurementDto;
import ru.danch.test.exceptions.ValidationException;
import ru.danch.test.repository.MeasurementRepository;

@Component
public class MeasurementHelper {

    private final MeasurementRepository measurementRepository;

    public MeasurementHelper(@Lazy MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public void validateRequest(MeasurementDto request) {
        measurementRepository.getRecent(request.getUserId(), request.getType()).ifPresent(recent -> {
            if (recent.getUsage().compareTo(request.getUsage()) > 0) {
                throw new ValidationException("Incorrect usage volume");
            }
        });
    }

}
