package com.cognizant.herobookapi.herobookapi.controllers;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class FanController extends HeroBookController{

    ArrayList<Hero> favouriteHerosList;

    @PostMapping("/api/favouriteHeroes")
    public ArrayList<Hero> createFavouriteHeroList() {

            favouriteHerosList = new ArrayList<>();

        return favouriteHerosList;
    }

    @PostMapping("/api/favouriteHero")
    public ArrayList<Hero> addFavouriteHero(@RequestBody Hero hero) {
        if(favouriteHerosList == null) {
            favouriteHerosList = new ArrayList<>();
        }
        favouriteHerosList.add(hero);
        return favouriteHerosList;
    }

    @DeleteMapping("/api/favouriteHeroes/{heroName}")
    public void createFavouriteHeroList(@PathVariable String heroName) {
        if(favouriteHerosList!=null) {
            Iterator<Hero> favoriteHero = favouriteHerosList.iterator();
            while (favoriteHero.hasNext()) {
                Hero hero = favoriteHero.next();
                if (hero.equals(heroName)) {
                    favoriteHero.remove();
                }
            }
        }

    }
}
