package com.laron.Livestock.management.service;


import com.laron.Livestock.management.dtos.CreateBreedRequest;
import com.laron.Livestock.management.entities.BreedEntity;

import java.util.List;

public interface BreedService {

    public List<BreedEntity> getAllBreeds();

    public BreedEntity createBreed(CreateBreedRequest breed);

    public String deleteBreedById(Long id);
}
