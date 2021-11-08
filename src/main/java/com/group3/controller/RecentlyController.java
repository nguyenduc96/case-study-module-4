package com.group3.controller;

import com.group3.models.music.Music;
import com.group3.models.recently.Recently;
import com.group3.models.user.User;
import com.group3.models.user.UserPrinciple;
import com.group3.services.music.IMusicService;
import com.group3.services.recently.IRecentlyService;
import com.group3.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/recently")
public class RecentlyController {
    @Autowired
    private IRecentlyService recentlyService;

    @Autowired
    private IMusicService musicService;

    @Autowired
    private IUserService userService;

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

    @PostMapping
    public ResponseEntity<Recently> addNew(@RequestBody Recently recently) {
        Recently recently1 =  recentlyService.save(recently);
        return new ResponseEntity<>(recently1, HttpStatus.CREATED);
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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/musics")
    public ResponseEntity<Set<Music>> recentlyByUser(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        Set<Music> musics = new HashSet<>();
        List<Recently> recents = recentlyService.findByUserId(user.getId());
        for (Recently r: recents) {
            musics.add(musicService.findById(r.getMusic().getId()).get());
        }
        return new ResponseEntity<>(musics, HttpStatus.OK);
    }
}
