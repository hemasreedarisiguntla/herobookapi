package com.cognizant.herobookapi.herobookapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VillianControllerTests {

    @Autowired
    MockMvc mockMvc;

    /**
     * As a visitor, I can view all the villains.
     *
     * When I view all the villains
     * Then I can see names of all villain
     * /api/villians -> 200  and all villians
     */
    @Test
    public void getAllVillians() throws Exception {
        mockMvc.perform(get("/api/villians"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("osborne"))
                .andExpect(jsonPath("$[1]").value("joker"));
    }

    /**
     * As a visitor, I can see information about any individual hero so that I can see their stats.
     *
     * Rule: Villains have an arch rival, image, real name, hero name, height, weight, special power, intelligence, strength, power, speed, agility, description, and story.
     *
     * Given I have the name of a villain
     * When I retreive the villain
     * Then I can view all the details for that villain
     * /api/villians/{villianName} -> 200 and villian details
     *
     * Given I have an incorrect villain name
     * When I retreive details for that villain
     * Then I receive a message that it doesn't exist
     */

    @Test
    public void getVillianByName() throws Exception {
        mockMvc.perform(get("/api/villians/{villianName}", "osborne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.heroName").value("osborne"))
                .andExpect(jsonPath("$.realName").value("green"))
                .andExpect(jsonPath("$.image").value("www.disney.com/villian.jpg"))
                .andExpect(jsonPath("$.height").value(6.1))
                .andExpect(jsonPath("$.strength").value(100))
                .andExpect(jsonPath("$.agility").value(true));

    }

    /**
     * Given I have an incorrect villain name
     * When I retreive details for that villain
     * Then I receive a message that it doesn't exist
     * /api/villians/{villianName} -> 404 and error message
     */
    @Test
    public void getVillianByName_NotFound() throws Exception {
        mockMvc.perform(get("/api/villians/{villianName}", "abc"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Villian not found"));
    }

}
