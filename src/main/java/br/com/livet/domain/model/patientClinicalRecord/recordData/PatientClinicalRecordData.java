package br.com.livet.domain.model.patientClinicalRecord.recordData;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PatientClinicalRecordData {
    private PersonalData personalData;
    private HealthHistory healthHistory;
    private PhysicalEvaluation physicalEvaluation;
    private PatientGoals patientGoals;
    private CarePlan carePlan;
}


