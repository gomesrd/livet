package br.com.livet.domain.model.patientClinicalRecord.recordData;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarePlan {

    private int classesPerWeek;
    private int classDurationMinutes;
    private String specificObjectives;
    private String strategy; // Ex: fortalecimento do core, reeducação postural

}
