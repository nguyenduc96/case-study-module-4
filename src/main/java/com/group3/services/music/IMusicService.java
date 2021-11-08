package com.group3.services.music;

import com.group3.models.category.Category;
import com.group3.models.music.Music;
import com.group3.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMusicService extends IGeneralService<Music> {
    Page<Music> findAllByNameContaining(String name, Pageable pageable);

    List<Music> findAllByPlaylistId( Long playlist_id);

    Page<Music> findAllByCategory(Category category, Pageable pageable);

    Page<Music> findAllByNameWithCategory(Long category_id, String name, Pageable pageable);

    List<Music> findMusicRandom();

    Page<Music> findMusicByUserId(Long user_id, Pageable pageable);

    Page<Music> findMusicByFavoriteAndUserId(Long user_id, Pageable pageable);
}
