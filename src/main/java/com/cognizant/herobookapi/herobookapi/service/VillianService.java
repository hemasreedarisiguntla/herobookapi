package com.cognizant.herobookapi.herobookapi.service;

import com.cognizant.herobookapi.herobookapi.entity.Villian;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VillianService {

    ArrayList<Villian> villianList;

    @Autowired
    ObjectMapper objectMapper;

    String villiansJsonPath = "src/test/java/data/villians.json";

    @PostConstruct
    public void setVillianList() throws IOException {
        File villiansFile = new File(villiansJsonPath);
        villianList = objectMapper.readValue(villiansFile, new TypeReference<ArrayList<Villian>>(){});
    }

    public List<String> getAllVillians() {
        List<String> villianNamesList = villianList.stream().map(Villian::getHeroName)
                .collect(Collectors.toList());
        return villianNamesList;
    }

    public Villian getVillianByName(String villianName) throws Exception {
        return villianList.stream().filter(villian -> villianName.equals(villian.getHeroName())).findAny().orElse(null);

    }
}
