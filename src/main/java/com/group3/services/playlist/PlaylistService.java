package com.group3.services.playlist;

import com.group3.models.playlist.Playlist;
import com.group3.repositories.playlist.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService{

    @Autowired
    private IPlaylistRepository playlistRepository;

    @Override
    public Iterable<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Page<Playlist> findAll(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public void remove(Long id) {
        playlistRepository.deleteById(id);
    }
}
