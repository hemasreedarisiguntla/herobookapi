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
public class VillianControllerTests {

    @Autowired
    MockMvc mockMvc;

    /**
     * As a visitor, I can view all the villains.
     *
     * When I view all the villains
     * Then I can see names of all villain
     */
    @Test
    public void getAllVillians() throws Exception {
        mockMvc.perform(get("/api/villians"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("osborne"))
                .andExpect(jsonPath("$[1]").value("joker"));
    }
}
