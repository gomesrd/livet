package br.com.livet.domain.service.PatientClinicalRecord;

import br.com.livet.domain.model.patientClinicalRecord.CreatePatientClinicalRecordRequest;
import br.com.livet.domain.port.patientClinicalRecord.PatientClinicalRecordRepositoryPort;
import br.com.livet.domain.port.patientClinicalRecord.PatientClinicalRecordServicePort;
import br.com.livet.infrastructure.entity.PatientClinicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientClinicalRecordService implements PatientClinicalRecordServicePort {
    private final PatientClinicalRecordRepositoryPort patientClinicalRecordRepositoryPort;

    @Override
    public PatientClinicalRecord create(CreatePatientClinicalRecordRequest createPatientClinicalRecordRequest) {
        // Implementation for creating a patient clinical record
        return patientClinicalRecordRepositoryPort.save(createPatientClinicalRecordRequest);
    }

    @Override
    public PatientClinicalRecord update(UUID id, CreatePatientClinicalRecordRequest patientClinicalRecord) {
        // Implementation for updating a patient clinical record
        return patientClinicalRecordRepositoryPort.update(patientClinicalRecord);
    }

    @Override
    public void delete(UUID id) {
        // Implementation for deleting a patient clinical record
        patientClinicalRecordRepositoryPort.delete(id);
    }

    @Override
    public PatientClinicalRecord findById(UUID id) {
        // Implementation for finding a patient clinical record by ID
        return patientClinicalRecordRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("Patient Clinical Record not found"));
    }

    @Override
    public List<PatientClinicalRecord> findAll() {
        // Implementation for finding all patient clinical records
        return patientClinicalRecordRepositoryPort.findAll();
    }
}