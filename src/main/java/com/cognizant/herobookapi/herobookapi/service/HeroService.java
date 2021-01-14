package com.cognizant.herobookapi.herobookapi.service;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroService {

    ArrayList<Hero> herosList;

    @Autowired
    ObjectMapper objectMapper;

    String herosJsonPath = "src/test/java/data/heros.json";

    @PostConstruct
    public void setHeroList() throws IOException {
        File heroesFile = new File(herosJsonPath);
        herosList = objectMapper.readValue(heroesFile, new TypeReference<ArrayList<Hero>>(){});
    }

    public List<String> getAllHeroes() {
        List<String> herosNamesList = herosList.stream().map(Hero::getHeroName)
                .collect(Collectors.toList());
        return herosNamesList;
    }

    public Hero getAHeroByName(String heroName) throws Exception {
        return herosList.stream().filter(hero -> heroName.equals(hero.getHeroName())).findAny().orElse(null);
    }

}
