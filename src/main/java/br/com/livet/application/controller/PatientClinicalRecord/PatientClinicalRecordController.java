package br.com.livet.application.controller.PatientClinicalRecord;

import br.com.livet.domain.model.patientClinicalRecord.CreatePatientClinicalRecordRequest;
import br.com.livet.domain.port.patientClinicalRecord.PatientClinicalRecordServicePort;
import br.com.livet.infrastructure.entity.PatientClinicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/patients-clinical-records")
@RequiredArgsConstructor
public class PatientClinicalRecordController {

    private final PatientClinicalRecordServicePort patientClinicalRecordService;

    @PostMapping
    public ResponseEntity<PatientClinicalRecord> create(@RequestBody CreatePatientClinicalRecordRequest createPatientClinicalRecordRequest) {
        return ResponseEntity.ok(patientClinicalRecordService.create(createPatientClinicalRecordRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientClinicalRecord> update(@PathVariable UUID id, @RequestBody CreatePatientClinicalRecordRequest createPatientClinicalRecordRequest) {
        return ResponseEntity.ok(patientClinicalRecordService.update(id, createPatientClinicalRecordRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        patientClinicalRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientClinicalRecord> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(patientClinicalRecordService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PatientClinicalRecord>> findAll() {
        return ResponseEntity.ok(patientClinicalRecordService.findAll());
    }

}
