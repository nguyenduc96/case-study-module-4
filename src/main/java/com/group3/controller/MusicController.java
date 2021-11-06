package com.group3.controller;

import com.group3.models.category.Category;
import com.group3.models.music.Music;
import com.group3.models.music.MusicRequest;
import com.group3.models.recently.Recently;
import com.group3.models.user.User;
import com.group3.models.user.UserPrinciple;
import com.group3.services.music.IMusicService;
import com.group3.services.recently.IRecentlyService;
import com.group3.services.user.IUserService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musics")
@CrossOrigin("*")
public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;

    private final String IMAGE_URL = "images/";

    private final String SONG_URL = "musics/";

    @Autowired
    private IMusicService musicService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecentlyService recentlyService;

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
    public ResponseEntity<Music> create(MusicRequest musicRequest, UserPrinciple userPrinciple) throws IOException {
        MultipartFile songMultipartFile = musicRequest.getSong();
        MultipartFile imageMultipartFile = musicRequest.getImage();
        long dateTime = new Date().getTime();
        String songLink = dateTime + songMultipartFile.getOriginalFilename();
        String imageLink = dateTime + imageMultipartFile.getOriginalFilename();
        FileCopyUtils.copy(songMultipartFile.getBytes(),
                new File(fileUpload + SONG_URL + songLink));
        FileCopyUtils.copy(imageMultipartFile.getBytes(),
                new File(fileUpload + IMAGE_URL + imageLink));
        Music music = new Music();
        music.setCategory(musicRequest.getCategory());
        music.setCreated(new Date());
        music.setName(musicRequest.getName());
        music.setDescription(musicRequest.getDescription());
        music.setImage(imageLink);
//        User user = getUserByUsernameWithUserPrinciple(userPrinciple);
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        music.setUser(user);
        music.setSong(songLink);
        musicService.save(music);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User getUserByUsernameWithUserPrinciple(UserPrinciple userPrinciple) {
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        return user;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> update(@PathVariable Long id, MusicRequest musicRequest, UserPrinciple userPrinciple) throws IOException {
        MultipartFile songMultipartFile = musicRequest.getSong();
        MultipartFile imageMultipartFile = musicRequest.getImage();
        long dateTime = new Date().getTime();
        String songLink = dateTime + songMultipartFile.getOriginalFilename();
        String imageLink = dateTime + imageMultipartFile.getOriginalFilename();
        FileCopyUtils.copy(songMultipartFile.getBytes(),
                new File(fileUpload + SONG_URL + songLink));
        FileCopyUtils.copy(imageMultipartFile.getBytes(),
                new File(fileUpload + IMAGE_URL + imageLink));
        Music music = new Music();
        music.setId(id);
        music.setCategory(musicRequest.getCategory());
        music.setName(musicRequest.getName());
        music.setDescription(musicRequest.getDescription());
        User user = getUserByUsernameWithUserPrinciple(userPrinciple);
        music.setUser(user);
        music.setImage(imageLink);
        music.setSong(songLink);
        musicService.save(music);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Music> delete(@PathVariable Long id) {
        Optional<Music> musicOptional = musicService.findById(id);
        musicService.remove(id);
        File file = new File(fileUpload + SONG_URL + musicOptional.get().getSong());
        file.delete();
        File file1 = new File(fileUpload + IMAGE_URL + musicOptional.get().getImage());
        file1.delete();
        return new ResponseEntity<>(musicOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> detail(@PathVariable Long id, UserPrinciple userPrinciple) {
//        User user = getUserByUsernameWithUserPrinciple(userPrinciple);

        Optional<Music> musicOptional = musicService.findById(id);
        Recently recently = new Recently();
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        recently.setUser(user);
        recently.setMusic(musicOptional.get());
        recentlyService.save(recently);
        return new ResponseEntity<>(musicOptional.get(), HttpStatus.OK);
    }
}
