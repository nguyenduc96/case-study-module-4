package com.group3.controller;

import com.group3.models.category.Category;
import com.group3.models.music.Music;
import com.group3.models.music.MusicRequest;
import com.group3.models.recently.Recently;
import com.group3.models.user.User;
import com.group3.models.user.UserPrinciple;
import com.group3.services.JwtService;
import com.group3.services.favorite.IFavoriteService;
import com.group3.services.music.IMusicService;
import com.group3.services.playlist.IPlaylistService;
import com.group3.services.recently.IRecentlyService;
import com.group3.services.user.IUserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
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

    private final String SONG_URL = "music/";

    @Autowired
    private IMusicService musicService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecentlyService recentlyService;

    @Autowired
    private IPlaylistService playlistService;

    @Autowired
    private IFavoriteService favoriteService;

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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<Music> create(MusicRequest musicRequest, Authentication authentication) throws IOException {
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
        User user = getUserByAuthentication(authentication);
        music.setUser(user);
        music.setName(musicRequest.getName());
        music.setDescription(musicRequest.getDescription());
        music.setImage(imageLink);
        music.setSong(songLink);
        musicService.save(music);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User getUserByAuthentication(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        return user;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/edit/{id}")
    public ResponseEntity<Music> getById(@PathVariable("id") Long id, Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        Optional<Music> music = musicService.findById(id);
        if (user.getId().equals(music.get().getUser().getId())) {
            return new ResponseEntity<>(music.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("/edit/{id}")
    public ResponseEntity<Music> update(@PathVariable Long id, MusicRequest musicRequest, Authentication authentication) throws IOException {
        User user = getUserByAuthentication(authentication);
        Optional<Music> musicOptional = musicService.findById(id);
        if (user.getId().equals(musicOptional.get().getUser().getId())) {
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
            music.setUser(user);
            music.setImage(imageLink);
            music.setSong(songLink);
            musicService.save(music);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Music> delete(@PathVariable Long id) {
        Optional<Music> musicOptional = musicService.findById(id);
        recentlyService.deleteByMusicId(id);
        favoriteService.deleteByMusicId(id);
        playlistService.deleteByMusicId(id);
        musicService.remove(id);
        File file = new File(fileUpload + SONG_URL + musicOptional.get().getSong());
        file.delete();
        File file1 = new File(fileUpload + IMAGE_URL + musicOptional.get().getImage());
        file1.delete();
        return new ResponseEntity<>(musicOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> detail(@PathVariable Long id, Authentication authentication) {
        Optional<Music> musicOptional = musicService.findById(id);
        Recently recently = new Recently();
        User user = getUserByAuthentication(authentication);
        recently.setUser(user);
        recently.setMusic(musicOptional.get());
        recentlyService.save(recently);
        return new ResponseEntity<>(musicOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Music>> getMusicRandom() {
        List<Music> musicList = musicService.findMusicRandom();
        return new ResponseEntity<>(musicList, HttpStatus.OK);
    }
}
