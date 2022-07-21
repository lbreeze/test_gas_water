package ru.danch.test.model;

import ru.danch.test.enums.MeasurementType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "measurement")
public class MeasurementEntity extends BaseEntity {

    private Long userId;
    @Enumerated(EnumType.STRING)
    private MeasurementType type;
    private BigDecimal usage;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MeasurementType getType() {
        return type;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }

    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }
}
