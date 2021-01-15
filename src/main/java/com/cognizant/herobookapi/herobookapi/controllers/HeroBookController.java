package com.cognizant.herobookapi.herobookapi.controllers;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.cognizant.herobookapi.herobookapi.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public class HeroBookController {

    @Autowired
    HeroService heroService;

    @GetMapping("/api/heroes")
    public ResponseEntity<Object> getAllHeroes() {
        return new ResponseEntity<>(heroService.getAllHeroes(),HttpStatus.OK);
    }

    @GetMapping("/api/heroes/{heroName}")
    public ResponseEntity<Object> getAHeroByName(@PathVariable String heroName) throws Exception {

        Hero hero = heroService.getAHeroByName(heroName);
        if (hero == null) {
            return new ResponseEntity<>("Hero not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(hero, HttpStatus.OK);
        }


    }
}
