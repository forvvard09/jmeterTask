package com.testgenerator.generatorPlant.repository;

import com.testgenerator.generatorPlant.model.Plant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Plants {
    private List<Plant> listPlant;

    public Plants() {
        this.listPlant = new ArrayList<>();
    }

    public List<Plant> getListPlant() {
        return listPlant;
    }

}
