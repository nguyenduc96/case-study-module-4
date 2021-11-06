package com.group3.repositories.category;

import com.group3.models.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * From category JOIN music m on category.id = m.category_id where m.id = ?1", nativeQuery = true)
    Category getCategoryByMusicId(@Param("1") Long musicId);
}
