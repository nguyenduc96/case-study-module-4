package com.group3.controller;


import com.group3.models.playlist.Playlist;
import com.group3.services.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private IPlaylistService playlistService;

    @GetMapping
    public ResponseEntity<Iterable<Playlist>> showPlaylist(){
        return new ResponseEntity<>(playlistService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist){
        return new ResponseEntity<>(playlistService.save(playlist),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist){
        Optional<Playlist> playlist1 = playlistService.findById(id);
        if(!playlist1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlist.setId(id);
        return new ResponseEntity<>(playlistService.save(playlist), HttpStatus.CREATED);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id){
        Optional<Playlist> playlist1 = playlistService.findById(id);
        if(!playlist1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }








}
