package com.group3.repositories.playlist;

import com.group3.models.music.Music;
import com.group3.models.playlist.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from playlist_music where music_id = ?1", nativeQuery = true)
    void deleteByMusicId(@Param("1") Long musicId);
}
