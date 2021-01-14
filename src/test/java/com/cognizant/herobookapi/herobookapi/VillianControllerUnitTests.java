package com.cognizant.herobookapi.herobookapi;

import com.cognizant.herobookapi.herobookapi.controllers.VillianController;
import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.cognizant.herobookapi.herobookapi.entity.Villian;
import com.cognizant.herobookapi.herobookapi.service.HeroService;
import com.cognizant.herobookapi.herobookapi.service.VillianService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VillianController.class)
public class VillianControllerUnitTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    VillianService villianService;

    ArrayList<String> villainsList;

    String villianJsonPath = "src/test/java/data/villian.json";

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

    @Test
    public void getAVillianByName() throws Exception {

        File villianFile = new File(villianJsonPath);
        Villian villian = objectMapper.readValue(villianFile, new TypeReference<Villian>(){});

        when(villianService.getVillianByName("osborne")).thenReturn(villian);
        mockMvc.perform(get("/api/villians/{villianName}", "osborne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.heroName").value("osborne"));
    }

    @Test
    public void getAVillianByName_NotFound() throws Exception {

        when(villianService.getVillianByName("abc")).thenReturn(null);
        mockMvc.perform(get("/api/villians/{villianName}", "abc"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Villian not found"));
    }

}
