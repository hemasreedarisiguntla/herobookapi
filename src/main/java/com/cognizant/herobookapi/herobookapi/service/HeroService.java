package com.cognizant.herobookapi.herobookapi.service;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
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

    public Hero getAHeroByName(String heroName) throws Exception {
        List<Hero> heroList = new ArrayList<>();
        heroList.add(new Hero("www.disney.com/spiderman.jpg", "Peter", "spider man",
                6.1, 75.0, "climbing", "10", 100, 600, 180, true, "saves world", "Hero"));

        return heroList.stream().filter(hero -> heroName.equals(hero.getHeroName())).findAny().orElse(null);
    }

}
