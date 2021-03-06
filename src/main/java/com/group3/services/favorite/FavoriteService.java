package com.group3.services.favorite;

import com.group3.models.favorite.Favorite;
import com.group3.repositories.favorite.IFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService implements IFavoriteService {
    @Autowired
    private IFavoriteRepository favoriteRepository;

    @Override
    public Iterable<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Page<Favorite> findAll(Pageable pageable) {
        return favoriteRepository.findAll(pageable);
    }

    @Override
    public Optional<Favorite> findById(Long id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void remove(Long id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public Page<Favorite> findByUserId(Long id, Pageable pageable) {
        return favoriteRepository.findByUserId(id, pageable);
    }

    @Override
    public void deleteByMusicId(Long musicId) {
        List<Favorite> favorites = favoriteRepository.findByMusicId(musicId);
        if (favorites != null) {
            favoriteRepository.deleteByMusicId(musicId);
        }
    }

    @Override
    public List<Favorite> findByMusicId(Long musicId) {
        return favoriteRepository.findByMusicId(musicId);
    }
}
