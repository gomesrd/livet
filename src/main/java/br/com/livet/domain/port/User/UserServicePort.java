package br.com.livet.domain.port.User;


import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.infrastructure.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserServicePort {
    User create(CreateUserRequest user);

    User update(UUID id, User user);

    void delete(UUID id);

    User findById(UUID id);

    List<User> findAll();
}
