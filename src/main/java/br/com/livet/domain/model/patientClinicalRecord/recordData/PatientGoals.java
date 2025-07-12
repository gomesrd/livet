package br.com.livet.domain.model.patientClinicalRecord.recordData;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PatientGoals {

    private boolean conditioning;
    private boolean rehabilitation;
    private boolean painRelief;
    private boolean posture;
    private boolean weightLoss;
    private boolean stressRelief;
    private String otherGoals; // Campo livre para descrição

    // Getters, setters, construtores etc.
}
