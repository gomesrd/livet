package br.com.livet.infrastructure.repository.user;

import br.com.livet.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAndDeletedFalse(String email);

    Optional<User> findByIdAndDeletedFalse(UUID id);

    List<User> findAllByDeletedFalse();
}