package br.com.livet.domain.port.patientClinicalRecord;


import br.com.livet.domain.model.patientClinicalRecord.CreatePatientClinicalRecordRequest;
import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.infrastructure.entity.PatientClinicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PatientClinicalRecordServicePort {
    PatientClinicalRecord create(CreatePatientClinicalRecordRequest createPatientClinicalRecordRequest);

    PatientClinicalRecord update(UUID id, CreatePatientClinicalRecordRequest patientClinicalRecord);

    void delete(UUID id);

    PatientClinicalRecord findById(UUID id);

    List<PatientClinicalRecord> findAll();
}
