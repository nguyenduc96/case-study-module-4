package com.group3.services.recently;

import com.group3.models.recently.Recently;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecentlyService implements IRecentlyService {
    @Override
    public Iterable<Recently> findAll() {
        return null;
    }

    @Override
    public Page<Recently> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Recently> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Recently save(Recently recently) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
