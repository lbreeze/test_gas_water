package ru.danch.test.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danch.test.dto.MeasurementDto;
import ru.danch.test.service.MeasurementService;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Measurement handling")
@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @Operation(description = "Get history for user measurements", operationId = "getHistory")
    @GetMapping(path = "/history/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MeasurementDto>> getHistory(
            @Parameter(description = "User identifier", required = true) @PathVariable Long userId) {
        return ResponseEntity.ok(measurementService.getHistoryOfUser(userId));
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MeasurementDto> postMeasurement(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Measurement info", required = true)
            @RequestBody
            @Valid
            MeasurementDto measurementDto) {
        return ResponseEntity.ok(measurementService.save(measurementDto));
    }
}
