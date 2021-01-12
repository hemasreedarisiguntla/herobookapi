package com.cognizant.herobookapi.herobookapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroBookControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    /**
     * Visitors
     *
     * As a visitor, I can view all the heroes.
     *
     * When I view all the heros
     * Then I can see names of all heros
     *
     * /api/heroes -- returns names of all heroes
     */

    @Test
    public void getAllHeroes() throws Exception {
        mockMvc.perform(get("/api/heroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("spider man"))
                .andExpect(jsonPath("$[1]").value("bat man"));
    }
}
