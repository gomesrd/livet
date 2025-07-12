package br.com.livet.infrastructure.repository.user;

import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.domain.port.user.UserRepositoryPort;
import br.com.livet.infrastructure.entity.User;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepository implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(CreateUserRequest user, String externalId) {
        return jpaUserRepository.save(
                User.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .externalId(externalId)
                        .build()
        );
    }


    @Override
    public User update(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public User findByIdOrElseThrow(UUID id) {
        return jpaUserRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAllByDeletedFalse();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmailAndDeletedFalse(email);
    }
}
