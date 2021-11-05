package com.group3.controller;


import com.group3.models.favorite.Favorite;
import com.group3.services.favorite.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private IFavoriteService favoriteService;


    @GetMapping
    public ResponseEntity<Iterable<Favorite>> allFavorite(){
        return new ResponseEntity<>(favoriteService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite){
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Favorite> favorite = favoriteService.findById(id);
        if(!favorite.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        favoriteService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
