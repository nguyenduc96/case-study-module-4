package com.group3.services.user;

import com.group3.models.user.User;
import com.group3.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    public Optional<User> findByUsername(String username);
    Page<User> findByUsernameContaining(String username, Pageable pageable);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
}
