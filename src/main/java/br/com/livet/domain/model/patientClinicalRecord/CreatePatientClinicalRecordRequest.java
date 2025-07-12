package br.com.livet.domain.model.patientClinicalRecord;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class CreatePatientClinicalRecordRequest {
    private UUID userId;
    private String userName;
    private UUID patientId;
    private String patientName;
    private JsonNode clinicalRecordData;
}
