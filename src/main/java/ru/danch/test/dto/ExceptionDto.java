package ru.danch.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Schema(description = "Exception response object")
public class ExceptionDto implements Serializable {

    @Schema(description = "Список ошибок")
    private final Set<String> errors = new HashSet<>();

    public ExceptionDto() {
    }

    public ExceptionDto(Set<String> errors) {
        this.errors.addAll(Objects.requireNonNull(errors));
    }

    public ExceptionDto(String message) {
        this.errors.add(Objects.requireNonNull(message));
    }

    public Set<String> getErrors() {
        return errors;
    }
}
