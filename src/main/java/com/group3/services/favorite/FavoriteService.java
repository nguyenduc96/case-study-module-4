package com.group3.services.favorite;

import com.group3.models.favorite.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteService implements IFavoriteService {
    @Override
    public Iterable<Favorite> findAll() {
        return null;
    }

    @Override
    public Page<Favorite> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Favorite> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Favorite save(Favorite favorite) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
