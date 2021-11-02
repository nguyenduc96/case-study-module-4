package com.group3.repositories.music;

import com.group3.models.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMusicRepository extends JpaRepository<Music, Long> {
}
