package com.group3.repositories.music;

import com.group3.models.category.Category;
import com.group3.models.music.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMusicRepository extends JpaRepository<Music, Long> {
    Page<Music> findAllByNameContaining(String name, Pageable pageable);

    Page<Music> findAllByCategory(Category category, Pageable pageable);

    @Query(value = "SELECT * FROM music m WHERE m.category_id = ?1 AND m.name LIKE ?2", nativeQuery = true)
    Page<Music> findAllByNameWithCategory(@Param("1") Long category_id, @Param("2") String name, Pageable pageable);

    @Query(value = "select * from music m join playlist_music pl on m.id = pl.music_id where pl.playlist_id = ?1", nativeQuery = true)
    List<Music> findAllByPlaylistId(@Param("1") Long playlist_id);
}
