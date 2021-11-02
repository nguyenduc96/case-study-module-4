package com.group3.services.role;

import com.group3.models.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Override
    public Iterable<Role> findAll() {
        return null;
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
