package br.com.livet.domain.model.patientClinicalRecord.recordData;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HealthHistory {

    private List<String> preExistingConditions; // Ex: hérnia de disco, hipertensão
    private String injuryOrSurgeryHistory;
    private String continuousMedication;
    private String posturalProblemsOrPain; // lombalgia, cervicalgia, etc.
    private String physicalActivityHistory;
    private String medicalRestrictions;

}
