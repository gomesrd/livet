package br.com.livet.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.livet.infrastructure.entity.Table.Table.USERS;
import static br.com.livet.infrastructure.entity.schema.Schema.LIVET;

@Entity
@Table(name = USERS, schema = LIVET)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name="role", nullable = false, length = 50)
    private String role;

    @Column(name="external_id", nullable = false, unique = true)
    private String externalId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.deleted = false;
        this.role = "PATIENT";
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
