package br.com.livet.infrastructure.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

import static br.com.livet.infrastructure.entity.Table.Table.PATIENTS_CLINICAL_RECORDS;
import static br.com.livet.infrastructure.entity.schema.Schema.LIVET;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = PATIENTS_CLINICAL_RECORDS, schema = LIVET)
public class PatientClinicalRecord {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "clinical_record_data", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode clinicalRecordData;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ColumnDefault("false")
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.deleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}