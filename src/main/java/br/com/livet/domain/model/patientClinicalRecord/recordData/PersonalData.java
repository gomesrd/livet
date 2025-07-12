package br.com.livet.domain.model.patientClinicalRecord.recordData;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PersonalData {

    private String fullName;
    private LocalDate birthDate;
    private String gender;
    private String fullAddress;
    private String phone;
    private String email;
    private String profession;

}
