package com.cognizant.herobookapi.herobookapi.controllers;

import com.cognizant.herobookapi.herobookapi.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeroBookController {

    @Autowired
    HeroService heroService;

    @GetMapping("/api/heroes")
    public List<String> getAllHeroes() {

        return heroService.getAllHeroes();
    }
}
