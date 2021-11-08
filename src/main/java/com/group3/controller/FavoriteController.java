package com.group3.controller;

import com.group3.models.favorite.Favorite;
import com.group3.services.favorite.IFavoriteService;
import com.group3.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin("*")
@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private IFavoriteService favoriteService;

    @Autowired
    private IUserService userService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<?> showAll(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(favoriteService.findAll(pageable), HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if(!favoriteService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(favoriteService.findById(id).get(), HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getFavoriteByUserId(@PathVariable Long id, Pageable pageable) {
        if(!userService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(favoriteService.findByUserId(id, pageable),HttpStatus.OK );
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<?> addNewFavorite(@RequestBody Favorite favorite) {
        return new ResponseEntity<>(favoriteService.save(favorite), HttpStatus.CREATED);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id) {
        Optional<Favorite> favoriteOptional = favoriteService.findById(id);
        if(!favoriteOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        favoriteService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping ("/{id}")
    public ResponseEntity<?> editFavorite(@PathVariable Long id, Favorite favorite) {
        Optional<Favorite> favoriteOptional = favoriteService.findById(id);
        if(!favoriteOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        favoriteService.save(favorite);
        return new ResponseEntity<>(favorite,HttpStatus.OK);
    }
}
