package com.group3.services.music;

import com.group3.models.music.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicService implements IMusicService {
    @Override
    public Iterable<Music> findAll() {
        return null;
    }

    @Override
    public Page<Music> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Music> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Music save(Music music) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
