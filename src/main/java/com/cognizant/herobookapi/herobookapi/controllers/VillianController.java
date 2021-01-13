package com.cognizant.herobookapi.herobookapi.controllers;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.cognizant.herobookapi.herobookapi.entity.Villian;
import com.cognizant.herobookapi.herobookapi.service.VillianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VillianController {
    @Autowired
    VillianService villianService;

    @GetMapping("/api/villians")
    public ResponseEntity<Object> getAllVillians() {
        return new ResponseEntity<>(villianService.getAllVillians(),HttpStatus.OK);
    }

    @GetMapping("/api/villians/{villianName}")
    public ResponseEntity<Object> getVillianByName(@PathVariable String villianName) throws Exception {

        Villian villian = villianService.getVillianByName(villianName);
        if (villian == null) {
            return new ResponseEntity<>("Villian not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(villian, HttpStatus.OK);
        }


    }
}
