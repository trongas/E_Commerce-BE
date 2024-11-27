package com.example.e_commerce.repository;

import java.util.Optional;

import com.example.e_commerce.entity.RefreshToken;
import com.example.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(User user); // Find by user to check for existing tokens

    @Modifying
    int deleteByUser(User user);
}