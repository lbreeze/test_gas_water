package ru.danch.test.repository;

import com.querydsl.jpa.JPAExpressions;
import org.springframework.stereotype.Repository;
import ru.danch.test.enums.MeasurementType;
import ru.danch.test.model.MeasurementEntity;

import java.util.Optional;

import static ru.danch.test.model.QMeasurementEntity.measurementEntity;

@Repository
public interface MeasurementRepository extends BaseRepository<MeasurementEntity> {

    Iterable<MeasurementEntity> findByUserId(Long userId);

    default Optional<MeasurementEntity> getRecent(Long userId, MeasurementType type) {

        var recentSubQuery = JPAExpressions.select(measurementEntity.id.max())
                .from(measurementEntity)
                .where(measurementEntity.userId.eq(userId)
                .and(measurementEntity.type.eq(type)));

        return findOne(measurementEntity.id.eq(recentSubQuery));
    }
}
