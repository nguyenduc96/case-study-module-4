package com.group3.repositories.favorite;

import com.group3.models.favorite.Favorite;
import com.group3.models.music.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {
    Page<Favorite> findByUserId(Long id, Pageable pageable);
}
