package com.cognizant.herobookapi.herobookapi;

import com.cognizant.herobookapi.herobookapi.controllers.HeroBookController;
import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.cognizant.herobookapi.herobookapi.service.HeroService;
import com.cognizant.herobookapi.herobookapi.service.VillianService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

@WebMvcTest(HeroBookController.class)
public class HeroBookControllerUnitTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HeroService heroService;

    ArrayList<String> herosList;

    @Test
    public void getAllHeroes_size_zero() throws Exception {

        herosList = new ArrayList<>();
        when(heroService.getAllHeroes()).thenReturn(herosList);
        mockMvc.perform(get("/api/heroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
