package ru.danch.test.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danch.test.dto.MeasurementDto;
import ru.danch.test.service.MeasurementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping(path = "/history/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MeasurementDto>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(measurementService.getHistoryOfUser(userId));
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MeasurementDto> postMeasurement(@RequestBody @Valid MeasurementDto measurementDto) {
        return ResponseEntity.ok(measurementService.save(measurementDto));
    }
}
