package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateAnimalRequest;
import com.laron.Livestock.management.dtos.SonsResponse;
import com.laron.Livestock.management.entities.AnimalEntity;

import java.util.List;

public interface AnimalService {


    public List<AnimalEntity> getAllAnimas();
    public AnimalEntity getAnimalById(Long id);
    public AnimalEntity createAnimal(CreateAnimalRequest animal);

    public String  deleteAnimal(Long id);
    public List<SonsResponse> getAnimalSons(Long id);


    public List<AnimalEntity> getFarmAnimals(Long id);

    public AnimalEntity updateAnimal(Long id, CreateAnimalRequest animal);
}
