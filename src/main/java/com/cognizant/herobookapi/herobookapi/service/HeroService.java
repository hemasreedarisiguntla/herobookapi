package com.cognizant.herobookapi.herobookapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeroService {


    public List<String> getAllHeroes() {
        List<String> heroList = new ArrayList<>();
        heroList.add("spider man");
        heroList.add("bat man");
        return heroList;
    }

}
