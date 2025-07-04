package br.com.livet.infrastructure.repository;

import br.com.livet.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<User, UUID> {
}
