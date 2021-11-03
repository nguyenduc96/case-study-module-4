package com.group3.services.music;

import com.group3.models.music.Music;
import com.group3.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMusicService extends IGeneralService<Music> {
    Page<Music> findAllByNameContaining(String name, Pageable pageable);

}
