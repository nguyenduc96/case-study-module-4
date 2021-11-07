package com.group3.services.playlist;

import com.group3.models.music.Music;
import com.group3.models.playlist.Playlist;
import com.group3.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlaylistService extends IGeneralService<Playlist> {
//    List<Music> findAllByPlaylistId(Long playlist_id);
}
