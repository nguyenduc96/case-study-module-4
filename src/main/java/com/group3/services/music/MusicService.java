package com.group3.services.music;

import com.group3.models.category.Category;
import com.group3.models.music.Music;
import com.group3.repositories.music.IMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService implements IMusicService {

    @Autowired
    IMusicRepository musicRepository;

    @Override
    public Iterable<Music> findAll() {
        return musicRepository.findAll();
    }

    @Override
    public Page<Music> findAll(Pageable pageable) {
        return musicRepository.findAll(pageable);
    }

    @Override
    public Optional<Music> findById(Long id) {
        return musicRepository.findById(id);
    }

    @Override
    public Music save(Music music) {
        return musicRepository.save(music);
    }

    @Override
    public void remove(Long id) {
        musicRepository.deleteById(id);
    }

    @Override
    public Page<Music> findAllByNameContaining(String name, Pageable pageable) {
        return musicRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<Music> findAllByCategory(Category category, Pageable pageable) {
        return musicRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Music> findAllByNameWithCategory(Long category_id, String name, Pageable pageable) {
        return musicRepository.findAllByNameWithCategory(category_id, name, pageable);
    }

    @Override
    public List<Music> findMusicRandom() {
        return musicRepository.findMusicRandom();
    }
}
