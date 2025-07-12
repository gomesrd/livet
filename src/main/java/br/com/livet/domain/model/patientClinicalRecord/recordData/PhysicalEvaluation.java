package br.com.livet.domain.model.patientClinicalRecord.recordData;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PhysicalEvaluation {

    private String staticAndDynamicPosture;  // Anotações ou fotos
    private String flexibilityTests;
    private String strengthTests;
    private String breathingEvaluation;
    private String balanceAndCoordination;
    private double weight;
    private double height;

    public double getBmi() {
        return height > 0 ? weight / (height * height) : 0;
    }

}
