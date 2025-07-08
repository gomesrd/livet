package br.com.livet.infrastructure.repository.patientClinicalRecord;

import br.com.livet.infrastructure.entity.PatientClinicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPatientClinicalRecordRepository extends JpaRepository<PatientClinicalRecord, UUID> {
}