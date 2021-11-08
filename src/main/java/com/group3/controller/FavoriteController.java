package com.group3.controller;


import com.group3.models.favorite.Favorite;
import com.group3.models.music.Music;
import com.group3.models.user.User;
import com.group3.models.user.UserPrinciple;
import com.group3.services.JwtService;
import com.group3.services.favorite.IFavoriteService;
import com.group3.services.music.IMusicService;
import com.group3.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private IFavoriteService favoriteService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMusicService musicService;

    @GetMapping
    public ResponseEntity<Iterable<Favorite>> allFavorite(){
        return new ResponseEntity<>(favoriteService.findAll(), HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite, Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        favorite.setUser(user);
        return new ResponseEntity<>(favoriteService.save(favorite), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorite> update(@PathVariable Long id, @RequestBody Favorite favorite){
        Optional<Favorite> favorite1 = favoriteService.findById(id);
        if(!favorite1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        favorite.setId(id);
        return new ResponseEntity<>(favoriteService.save(favorite), HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Favorite> favorite = favoriteService.findById(id);
        if(!favorite.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        favoriteService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/musics")
    public ResponseEntity<Page<Music>> getMusicByUserWithFavorite(Authentication authentication, Pageable pageable) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        return new ResponseEntity<>(musicService.findMusicByFavoriteAndUserId(user.getId(), pageable), HttpStatus.OK);
    }

//
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
//    @GetMapping("/user")
//    public ResponseEntity<Iterable<Favorite>> getUserFavorites(Authentication authentication){
//        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
//        User user = userService.findByUsername(userPrinciple.getUsername()).get();
//        return new ResponseEntity<>(favoriteService.findByUser(user), HttpStatus.OK);
//    }
}
