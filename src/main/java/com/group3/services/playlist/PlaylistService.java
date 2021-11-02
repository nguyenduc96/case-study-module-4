package com.group3.services.playlist;

import com.group3.models.playlist.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService{
    @Override
    public Iterable<Playlist> findAll() {
        return null;
    }

    @Override
    public Page<Playlist> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Playlist save(Playlist playlist) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
