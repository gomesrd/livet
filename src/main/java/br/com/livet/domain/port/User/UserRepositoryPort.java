package br.com.livet.domain.port.User;

import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.infrastructure.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserRepositoryPort {
    User save(CreateUserRequest user, String externalId);
    User update(CreateUserRequest user);
    void deleteById(UUID id);
    Optional<User> findById(UUID id);
    List<User> findAll();
    Optional<User> findByEmail(String email);
}
