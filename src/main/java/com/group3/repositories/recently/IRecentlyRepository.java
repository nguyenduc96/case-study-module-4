package com.group3.repositories.recently;

import com.group3.models.playlist.Playlist;
import com.group3.models.recently.Recently;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IRecentlyRepository extends JpaRepository<Recently, Long> {
    @Query(value = "SELECT r.music_id, COUNT(id) AS views FROM recently r GROUP BY r.music_id ORDER BY views DESC LIMIT 15;", nativeQuery = true)
    List<Long> allMusicsViews();

    List<Recently> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from recently where music_id = ?1", nativeQuery = true)
    void deleteByMusicId(@Param("1")Long musicId);

    List<Recently> findByMusicId(Long musicId);
}
