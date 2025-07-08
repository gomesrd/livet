package br.com.livet.infrastructure.repository.patientClinicalRecord;

import br.com.livet.domain.model.patientClinicalRecord.CreatePatientClinicalRecordRequest;
import br.com.livet.domain.port.patientClinicalRecord.PatientClinicalRecordRepositoryPort;
import br.com.livet.infrastructure.entity.PatientClinicalRecord;
import br.com.livet.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PatientClinicalRecordRepository implements PatientClinicalRecordRepositoryPort {

    private final JpaPatientClinicalRecordRepository jpaPatientClinicalRecordRepository;

    @Override
    public PatientClinicalRecord save(CreatePatientClinicalRecordRequest patientClinicalRecord, String externalId) {
        return jpaPatientClinicalRecordRepository.save(
                PatientClinicalRecord.builder()
                        .userId(patientClinicalRecord.getUserId())
                        .userName(patientClinicalRecord.getUserName())
                        .patientId(patientClinicalRecord.getPatientId())
                        .patientName(patientClinicalRecord.getPatientName())
                        .clinicalRecordData(patientClinicalRecord.getClinicalRecordData())
                        .build()
        );
    }


    @Override
    public PatientClinicalRecord update(CreatePatientClinicalRecordRequest patientClinicalRecord) {
        return jpaPatientClinicalRecordRepository.save(
                PatientClinicalRecord.builder()
                        .userId(patientClinicalRecord.getUserId())
                        .userName(patientClinicalRecord.getUserName())
                        .patientId(patientClinicalRecord.getPatientId())
                        .patientName(patientClinicalRecord.getPatientName())
                        .clinicalRecordData(patientClinicalRecord.getClinicalRecordData())
                        .build()
        );
    }

    @Override
    public void deleteById(UUID id) {
        jpaPatientClinicalRecordRepository.deleteById(id);
    }

    @Override
    public Optional<PatientClinicalRecord> findById(UUID id) {
        return jpaPatientClinicalRecordRepository.findById(id);
    }

    @Override
    public List<PatientClinicalRecord> findAll() {
        return jpaPatientClinicalRecordRepository.findAll();
    }

}
