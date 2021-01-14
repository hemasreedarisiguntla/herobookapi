package com.cognizant.herobookapi.herobookapi;

import com.cognizant.herobookapi.herobookapi.controllers.VillianController;
import com.cognizant.herobookapi.herobookapi.service.HeroService;
import com.cognizant.herobookapi.herobookapi.service.VillianService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VillianController.class)
public class VillianControllerUnitTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VillianService villianService;

    ArrayList<String> villainsList;

    @Test
    public void getAllVillians_size_zero() throws Exception {

        villainsList = new ArrayList<>();
        when(villianService.getAllVillians()).thenReturn(villainsList);
        mockMvc.perform(get("/api/villians"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getAllVillians_size_one() throws Exception {
        villainsList = new ArrayList<>();
        villainsList.add("osborne");
        when(villianService.getAllVillians()).thenReturn(villainsList);
        mockMvc.perform(get("/api/villians"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
