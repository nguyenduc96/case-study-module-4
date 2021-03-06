package com.group3.controller;

import com.group3.models.category.Category;
import com.group3.models.music.Music;
import com.group3.services.category.ICategoryService;
import com.group3.services.music.IMusicService;
import com.group3.services.recently.IRecentlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IMusicService musicService;

    @Autowired
    private IRecentlyService recentlyService;

    @GetMapping
    public ResponseEntity<Page<Category>> getAll(Pageable pageable) {
        Page<Category> categoryPage = categoryService.findAll(pageable);
        if (categoryPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> detail(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/musics")
    public ResponseEntity<Page<Music>> getAllByMusic(@PathVariable Long id, @RequestParam(name = "q",required = false) String q, Pageable pageable) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        Page<Music> musicPage;
        if (q != null) {
            musicPage = musicService.findAllByNameWithCategory(categoryOptional.get().getId(), q, pageable);
        } else {
            musicPage = musicService.findAllByCategory(categoryOptional.get(), pageable);
        }
        return new ResponseEntity<>(musicPage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        if (category.getId() == null) {
            category.setId(id);
        }
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<Set<Category>> getTopMusic() {
        List<Long> ids = recentlyService.allMusicsViews();
        Set<Category> categories = new HashSet<>();
        for (Long musicId : ids) {
            Category category = categoryService.getCategoryByMusicId(musicId);
            categories.add(category);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
