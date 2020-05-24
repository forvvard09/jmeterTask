package com.testgenerator.generatorPlant.service;

import com.testgenerator.generatorPlant.repository.Plants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GeneratorPlant {

    @Autowired
    Plants plants;

    public String getRandomPlant() {
        String plant = plants.getListPlant().get(getRandomNumber()).toString();
        return plant;
    }

    private int getRandomNumber() {
        int min = 0;
        int max = plants.getListPlant().size();
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff);
        i += min;
        return i;
    }
}
