package com.group3.services.vip;

import com.group3.models.vip.Vip;
import com.group3.repositories.vip.IVipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VipService implements IVipService {
    @Autowired
    private IVipRepository vipRepository;

    @Override
    public Iterable<Vip> findAll() {
        return vipRepository.findAll();
    }

    @Override
    public Page<Vip> findAll(Pageable pageable) {
        return vipRepository.findAll(pageable);
    }

    @Override
    public Optional<Vip> findById(Long id) {
        return vipRepository.findById(id);
    }

    @Override
    public Vip save(Vip vip) {
        return vipRepository.save(vip);
    }

    @Override
    public void remove(Long id) {
        vipRepository.deleteById(id);
    }
}
