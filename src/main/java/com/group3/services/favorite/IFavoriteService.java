package com.group3.services.favorite;

import com.group3.models.favorite.Favorite;
import com.group3.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFavoriteService extends IGeneralService<Favorite> {
    Page<Favorite> findByUserId(Long id, Pageable pageable);
}
