package com.group3.controller;

import com.group3.models.music.Music;
import com.group3.models.recently.Recently;
import com.group3.services.music.IMusicService;
import com.group3.services.recently.IRecentlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/recently")
public class RecentlyController {
    @Autowired
    private IRecentlyService recentlyService;

    @Autowired
    private IMusicService musicService;

    @GetMapping
    public ResponseEntity<Iterable<Recently>> getAll() {
        return new ResponseEntity<>(recentlyService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recently> update(@PathVariable Long id, Recently recently) {
        if (recently.getId() == null) {
            recently.setId(id);
        }
        return new ResponseEntity<>(recentlyService.save(recently), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recently> delete(@PathVariable Long id) {
        recentlyService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/views")
    public ResponseEntity<List<Long>> views() {
        return new ResponseEntity<>(recentlyService.allMusicsViews(), HttpStatus.OK);
    }


    @GetMapping("/musics")
    public ResponseEntity<List<Music>> recently() {
        List<Long> ids = recentlyService.allMusicsViews();
        List<Music> musics = new ArrayList<>();
        for (Long l: ids) {
            Optional<Music> musicOptional = musicService.findById(l);
            musics.add(musicOptional.get());
        }
        return new ResponseEntity<>(musics, HttpStatus.OK);
    }
}
