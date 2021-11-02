package com.group3.repositories.favorite;

import com.group3.models.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {
}
