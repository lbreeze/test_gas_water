package ru.danch.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.danch.test.enums.MeasurementType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Measurement info")
public class MeasurementDto {

    @Schema(description = "Record idenfifier for update")
    private Long id;

    @Schema(description = "User idenfifier", required = true)
    @NotNull
    private Long userId;

    @Schema(description = "Resource type", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private MeasurementType type;

    @Schema(description = "Usage volume", required = true)
    @NotNull
    @DecimalMin("0")
    private BigDecimal usage;

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
