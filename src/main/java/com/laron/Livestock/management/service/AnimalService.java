package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateAnimalRequest;
import com.laron.Livestock.management.entities.AnimalEntity;

import java.util.List;

public interface AnimalService {


    public List<AnimalEntity> getAllAnimas();
    public AnimalEntity getAnimalById(Long id);
    public AnimalEntity createAnimal(CreateAnimalRequest animal);

    public String  deleteAnimal(Long id);
}
