package com.cognizant.herobookapi.herobookapi.service;

import com.cognizant.herobookapi.herobookapi.entity.Hero;
import com.cognizant.herobookapi.herobookapi.entity.Villian;
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

    public Villian getVillianByName(String villianName) throws Exception {
        List<Villian> villianList = new ArrayList<>();
        villianList.add(new Villian("osborne","www.disney.com/villian.jpg", "Peter", "osborne",
                6.1, 75.0, "climbing", "10", 100, 600, 180, true, "saves world", "Hero"));

        return villianList.stream().filter(villian -> villianName.equals(villian.getHeroName())).findAny().orElse(null);
    }
}
