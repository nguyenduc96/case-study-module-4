package com.group3.repositories.user;

import com.group3.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Page<User> findByUsernameContaining(String username, Pageable pageable);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
}
