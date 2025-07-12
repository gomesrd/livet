package br.com.livet.domain.port.patientClinicalRecord;

import br.com.livet.domain.model.patientClinicalRecord.CreatePatientClinicalRecordRequest;
import br.com.livet.infrastructure.entity.PatientClinicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface PatientClinicalRecordRepositoryPort {
    PatientClinicalRecord save(CreatePatientClinicalRecordRequest createPatientClinicalRecordRequest);
    PatientClinicalRecord update(CreatePatientClinicalRecordRequest createPatientClinicalRecordRequest);
    void delete(UUID id);
    Optional<PatientClinicalRecord> findById(UUID id);
    List<PatientClinicalRecord> findAll();
}
