package com.Ecommerce.UserAPI.infrastructure.Repositories;

import com.Ecommerce.UserAPI.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByNome(String nome);

    UserEntity findByVerificationCode(String verificationCode);

    boolean existsByEmail(String email);

    Optional<UserDetails> findByEmail(String email);

    @Transactional
    void deleteByNome(String nome);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByEmail(String email);


}
