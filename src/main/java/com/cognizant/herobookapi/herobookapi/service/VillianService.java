package com.cognizant.herobookapi.herobookapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VillianService {

    public List<String> getAllVillians() {
        List<String> villianList = new ArrayList<>();
        villianList.add("osborne");
        villianList.add("joker");
        return villianList;
    }
}
