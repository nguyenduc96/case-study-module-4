package com.group3.repositories.favorite;

import com.group3.models.favorite.Favorite;
import com.group3.models.recently.Recently;
import com.group3.models.music.Music;
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
public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {
    Page<Favorite> findByUserId(Long id, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "delete from favorite where music_id = ?1", nativeQuery = true)
    void deleteByMusicId(@Param("1")Long musicId);

    List<Favorite> findByMusicId(Long musicId);
}
