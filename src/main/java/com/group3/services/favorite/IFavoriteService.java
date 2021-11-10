package com.group3.services.favorite;

import com.group3.models.favorite.Favorite;
import com.group3.models.recently.Recently;
import com.group3.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFavoriteService extends IGeneralService<Favorite> {
    Page<Favorite> findByUserId(Long id, Pageable pageable);
    void deleteByMusicId(Long musicId);

    List<Favorite> findByMusicId(Long musicId);
}
