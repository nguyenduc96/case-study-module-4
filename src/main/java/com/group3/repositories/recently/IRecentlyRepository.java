package com.group3.repositories.recently;

import com.group3.models.recently.Recently;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecentlyRepository extends JpaRepository<Recently, Long> {
    @Query(value = "SELECT r.music_id, COUNT(id) AS views FROM recently r GROUP BY r.music_id ORDER BY views DESC LIMIT 15;", nativeQuery = true)
    List<Long> allMusicsViews();
}
