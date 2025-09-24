package com.certifai.login.gateway.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<Long, UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
