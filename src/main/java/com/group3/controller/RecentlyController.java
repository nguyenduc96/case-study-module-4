package com.group3.controller;

import com.group3.models.recently.Recently;
import com.group3.services.recently.IRecentlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/recently")
public class RecentlyController {
    @Autowired
    private IRecentlyService recentlyService;

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
}
