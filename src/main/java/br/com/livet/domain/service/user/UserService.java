package br.com.livet.domain.service.user;

import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.domain.port.User.UserRepositoryPort;
import br.com.livet.domain.port.User.UserServicePort;
import br.com.livet.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User create(CreateUserRequest user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        User existing = userRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existing.setFirstName(user.getFirstName());

        return userRepositoryPort.save(
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
}
