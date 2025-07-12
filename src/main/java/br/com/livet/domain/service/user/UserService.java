package br.com.livet.domain.service.user;

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
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;
    private final AuthService authService;

    @Override
    public User create(CreateUserRequest user) {
        String firebaseUid = authService.createUser(user);
        return userRepositoryPort.save(user, firebaseUid);
    }

    @Override
    public User update(UUID id, User user) {
        User userEntity = userRepositoryPort.findByIdOrElseThrow(id);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        return userRepositoryPort.update(userEntity);
    }

    @Override
    public void delete(UUID id) {
        User userEntity = userRepositoryPort.findByIdOrElseThrow(id);
        userEntity.setDeleted(true);
        userRepositoryPort.update(userEntity);
    }

    @Override
    public User findByIdOrElseThrow(UUID id) {
        return userRepositoryPort.findByIdOrElseThrow(id);
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
