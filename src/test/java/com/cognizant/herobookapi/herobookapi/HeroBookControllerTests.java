package com.cognizant.herobookapi.herobookapi;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.cognizant.herobookapi.herobookapi.service.HeroService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroBookControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    HeroService heroService;

    @Autowired
    ObjectMapper objectMapper;

    String herosJsonPath = "src/test/java/data/heros.json";

    @BeforeEach
    void setup() throws IOException {
        File heroesFile = new File(herosJsonPath);
        herosList = objectMapper.readValue(heroesFile, new TypeReference<ArrayList<Hero>>(){});
        heroService.setHeroList(herosList);
    }

    ArrayList<Hero> herosList;

    /**
     * Visitors
     * <p>
     * As a visitor, I can view all the heroes.
     * <p>
     * When I view all the heros
     * Then I can see names of all heros
     * <p>
     * /api/heroes -- returns names of all heroes
     */

    @Test
    public void getAllHeroes() throws Exception {

       mockMvc.perform(get("/api/heroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("spider man"))
                .andExpect(jsonPath("$[1]").value("bat man"));
    }

    /**
     * As a visitor, I can see information about any individual hero so that I can see their stats.
     * <p>
     * Rule: Heroes have an image, real name, hero name, height, weight, special power, intelligence, strength, power, speed, agility, description, and story.
     * <p>
     * Given I have the name of a hero
     * When I retreive the hero
     * Then I can view all the details for that hero
     * /api/heroes/{heroName} --> Hero 200 response status
     * <p>
     * Given I have an incorrect hero name
     * When I retreive details for that hero
     * Then I receive a message that it doesn't exist
     * /api/heroes/{heroName} --> "Hero doesn't exist" 400 bad request
     */
    @Test
    public void getAHeroByName() throws Exception {

        mockMvc.perform(get("/api/heroes/{heroName}", "spider man"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.heroName").value("spider man"))
                .andExpect(jsonPath("$.realName").value("Peter"))
                .andExpect(jsonPath("$.image").value("www.disney.com/spiderman.jpg"))
                .andExpect(jsonPath("$.height").value(6.1))
                .andExpect(jsonPath("$.strength").value(100))
                .andExpect(jsonPath("$.agility").value(true));

    }

    @Test
    public void getAHeroByNameNotFound() throws Exception {
        mockMvc.perform(get("/api/heroes/{heroName}", "wonder woman"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Hero not found"));
    }

}
