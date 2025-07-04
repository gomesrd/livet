package br.com.livet.infrastructure.repository;

import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.domain.port.User.UserRepositoryPort;
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
    public User save(CreateUserRequest user) {
        return jpaUserRepository.save(
                User.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .build()
        );
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
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }
}
