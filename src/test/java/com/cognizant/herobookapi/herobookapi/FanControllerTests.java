package com.cognizant.herobookapi.herobookapi;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.File;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FanControllerTests extends HeroBookControllerTests{

    String heroJsonPath = "src/test/java/data/hero.json";

    @Autowired
    ObjectMapper objectMapper;
    /**
     * Fans can do everything Visitors can do and more.
     *
     * As a fan, I can create a list of my favorite heroes so that I can share it with others.
     *
     * Given I have a user account
     * When I create a new Favorites list
     * Then the list is empty initially.
     *
     */

    @Test
    public void createFavouriteHerosList() throws Exception {

        mockMvc.perform(post("/api/favouriteHeroes").content(""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    /**
     * Given I have a Favorites list
     * When I add a hero to it
     * Then I should see it when I view it.
     *
     */

    @Test
    public void addFavoriteHero() throws Exception {
        File heroFile = new File(heroJsonPath);
        Hero hero = objectMapper.readValue(heroFile, new TypeReference<Hero>(){});

        mockMvc.perform(post("/api/favouriteHero")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(hero)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].heroName").value("spider man"));
    }

    /**
     * Given I have added a hero to my Favorites list
     * When I remove a hero from the list
     * Then I don't see it on my list anymore.
     */

    @Test
    public void deleteFavoriteHero() throws Exception {

        mockMvc.perform(delete("/api/favouriteHeroes/{heroName}","spider man"))
                .andExpect(status().isOk());
    }
}
