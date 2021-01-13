package com.cognizant.herobookapi.herobookapi.controllers;

import com.cognizant.herobookapi.herobookapi.service.VillianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VillianController {
    @Autowired
    VillianService villianService;

    @GetMapping("/api/villians")
    public List<String> getAllVillians() {
        return villianService.getAllVillians();
    }
}
