package com.group3.services.vip;

import com.group3.models.vip.Vip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VipService implements IVipService {
    @Override
    public Iterable<Vip> findAll() {
        return null;
    }

    @Override
    public Page<Vip> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Vip> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Vip save(Vip vip) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
