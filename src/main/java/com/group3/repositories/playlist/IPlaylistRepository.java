package com.group3.repositories.playlist;

import com.group3.models.playlist.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
}
