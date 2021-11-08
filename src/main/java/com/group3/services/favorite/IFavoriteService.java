package com.group3.services.favorite;

import com.group3.models.favorite.Favorite;
import com.group3.models.recently.Recently;
import com.group3.services.IGeneralService;

import java.util.List;

public interface IFavoriteService extends IGeneralService<Favorite> {
    void deleteByMusicId(Long musicId);

    List<Favorite> findByMusicId(Long musicId);
}
