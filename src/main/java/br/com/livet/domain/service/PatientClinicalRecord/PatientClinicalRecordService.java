package br.com.livet.domain.service.PatientClinicalRecord;

import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.domain.port.user.UserRepositoryPort;
import br.com.livet.domain.port.user.UserServicePort;
import br.com.livet.domain.service.auth.AuthService;
import br.com.livet.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientClinicalRecordService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;
    private final AuthService authService;

    @Override
    public User create(CreateUserRequest user) {
        String firebaseUid = authService.createUser(user);
        return userRepositoryPort.save(user, firebaseUid);
    }

    @Override
    public User update(UUID id, User user) {
        User existing = userRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existing.setFirstName(user.getFirstName());

        return userRepositoryPort.update(
                CreateUserRequest.builder()
                        .firstName(existing.getFirstName())
                        .lastName(existing.getLastName())
                        .build()
        );
    }

    @Override
    public void delete(UUID id) {
        userRepositoryPort.deleteById(id);
    }

    @Override
    public User findById(UUID id) {
        return userRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepositoryPort.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepositoryPort.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
