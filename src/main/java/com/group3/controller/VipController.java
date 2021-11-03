package com.group3.controller;

import com.group3.models.vip.Vip;
import com.group3.services.vip.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/vips")
public class VipController {
    @Autowired
    private IVipService vipService;

    @GetMapping()
    public ResponseEntity<Iterable<Vip>> showAllVips() {
        Iterable<Vip> vips = vipService.findAll();
        return new ResponseEntity<>(vips, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Vip> addNewVip(@RequestBody Vip vip) {
        Vip vipResponse = vipService.save(vip);
        return new ResponseEntity<>(vipResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVip(@PathVariable Long id) {
        vipService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vip> updateVip(@PathVariable Long id,@RequestBody Vip vip ) {
        if (vip.getId() == null) {
            vip.setId(id);
        }
        Vip vipResponse = vipService.save(vip);
        return new ResponseEntity<>(vipResponse, HttpStatus.OK);
    }
}
