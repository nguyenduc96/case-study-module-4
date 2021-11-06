package com.group3.services.recently;

import com.group3.models.recently.Recently;
import com.group3.repositories.recently.IRecentlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecentlyService implements IRecentlyService {
    @Autowired
    private IRecentlyRepository recentlyRepository;

    @Override
    public Iterable<Recently> findAll() {
        return recentlyRepository.findAll();
    }

    @Override
    public Page<Recently> findAll(Pageable pageable) {
        return recentlyRepository.findAll(pageable);
    }

    @Override
    public Optional<Recently> findById(Long id) {
        return recentlyRepository.findById(id);
    }

    @Override
    public Recently save(Recently recently) {
        return recentlyRepository.save(recently);
    }

    @Override
    public void remove(Long id) {
        recentlyRepository.deleteById(id);
    }

    @Override
    public List<Long> allMusicsViews() {
        return recentlyRepository.allMusicsViews();
    }
}
