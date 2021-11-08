package com.group3.controller;

import com.group3.models.music.Music;
import com.group3.models.playlist.Playlist;
import com.group3.models.recently.Recently;
import com.group3.services.music.IMusicService;
import com.group3.services.playlist.IPlaylistService;
import com.group3.services.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlists")
public class  PlaylistController {
    @Autowired
    private IPlaylistService playlistService;

    @Autowired
    private IMusicService musicService;

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getAll(@PathVariable Long id) {
       return new ResponseEntity<>(playlistService.findById(id).get(), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Playlist> update(@PathVariable Long id, Playlist playlist) {
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Playlist> delete(@PathVariable Long id) {
//        playlistService.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Recently> addNew(@RequestBody Playlist playlist) {
//
//    }
}
