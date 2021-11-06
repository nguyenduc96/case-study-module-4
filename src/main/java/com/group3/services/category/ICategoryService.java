package com.group3.services.category;

import com.group3.models.category.Category;
import com.group3.services.IGeneralService;
import org.springframework.data.repository.query.Param;

public interface ICategoryService extends IGeneralService<Category> {
    Category getCategoryByMusicId(Long musicId);
}
