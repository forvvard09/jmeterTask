package com.testgenerator.generatorPlant.controller;

import com.testgenerator.generatorPlant.service.GeneratorPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("plant")
public class PlantController {

    @Autowired
    GeneratorPlant generatorPlant;

    @GetMapping
    public String getPlant() {
        String plant = generatorPlant.getRandomPlant();
        return plant;
    }
}
