package com.group3.controller;

import com.group3.models.music.Music;
import com.group3.models.music.MusicRequest;
import com.group3.services.music.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/musics")
public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;

    private final String IMAGE_URL = "images/";

    private final String SONG_URL = "musics/";

    @Autowired
    private IMusicService musicService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "m") Optional<String> m, @PageableDefault(size = 5) Pageable pageable) {
        Page<Music> musicPage;
        if (m.isPresent()) {
            musicPage = musicService.findAllByNameContaining(m.get(), pageable);
        } else {
            musicPage = musicService.findAll(pageable);
        }
        return new ResponseEntity<>(musicPage, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Music> create(MusicRequest musicRequest) throws IOException {
        MultipartFile songMultipartFile = musicRequest.getSong();
        MultipartFile imageMultipartFile = musicRequest.getImage();
        long dateTime = new Date().getTime();
        String songLink = dateTime + songMultipartFile.getOriginalFilename();
        String imageLink = dateTime +  imageMultipartFile.getOriginalFilename();
        FileCopyUtils.copy(songMultipartFile.getBytes(),
                new File(fileUpload + SONG_URL +  songLink));
        FileCopyUtils.copy(imageMultipartFile.getBytes(),
                new File(fileUpload + IMAGE_URL + imageLink));
        Music music = new Music();
        music.setCategory(musicRequest.getCategory());
        music.setCreated(new Date());
        music.setName(musicRequest.getName());
        music.setDescription(musicRequest.getDescription());
        music.setUser(musicRequest.getUser());
        music.setImage(imageLink);
        music.setSong(songLink);
        musicService.save(music);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Music> update(@PathVariable Long id, MusicRequest musicRequest) throws IOException {
        MultipartFile songMultipartFile = musicRequest.getSong();
        MultipartFile imageMultipartFile = musicRequest.getImage();
        long dateTime = new Date().getTime();
        String songLink = dateTime + songMultipartFile.getOriginalFilename();
        String imageLink = dateTime +  imageMultipartFile.getOriginalFilename();
        FileCopyUtils.copy(songMultipartFile.getBytes(),
                new File(fileUpload + SONG_URL +  songLink));
        FileCopyUtils.copy(imageMultipartFile.getBytes(),
                new File(fileUpload + IMAGE_URL + imageLink));
        Music music = new Music();
        music.setId(musicRequest.getId());
        music.setCategory(musicRequest.getCategory());
        music.setName(musicRequest.getName());
        music.setDescription(musicRequest.getDescription());
        music.setUser(musicRequest.getUser());
        music.setImage(imageLink);
        music.setSong(songLink);
        musicService.save(music);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Music> delete(@PathVariable Long id) {
        Optional<Music> musicOptional = musicService.findById(id);
        musicService.remove(id);
        return new ResponseEntity<>(musicOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Music> detail(@PathVariable Long id) {
        Optional<Music> musicOptional = musicService.findById(id);
        return new ResponseEntity<>(musicOptional.get(), HttpStatus.OK);
    }
}
