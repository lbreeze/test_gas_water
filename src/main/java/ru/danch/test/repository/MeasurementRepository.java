package ru.danch.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.danch.test.model.MeasurementEntity;

@Repository
public interface MeasurementRepository extends CrudRepository<MeasurementEntity, Long> {

    Iterable<MeasurementEntity> findByUserId(Long userId);
}
