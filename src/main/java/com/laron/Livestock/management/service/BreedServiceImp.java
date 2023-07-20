package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateBreedRequest;
import com.laron.Livestock.management.entities.BreedEntity;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.repo.BreedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class BreedServiceImp  implements  BreedService{


    private final BreedRepository breedRepo;

    public BreedServiceImp(BreedRepository breedRepo) {
        this.breedRepo = breedRepo;
    }



    @Override
    public List<BreedEntity> getAllBreeds() {
        return StreamSupport.stream( breedRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public BreedEntity createBreed(CreateBreedRequest breed) {

        var createBreed = BreedEntity.builder()
                .name(breed.getName())
                .build();

        return breedRepo.save(createBreed);

    }



    @Override
    public String deleteBreedById(Long id) {

        var breed = breedRepo.findById(id).orElseThrow( ()-> new ResourceNotFound("The Breed with id:" + id + " was not found"));


            breedRepo.delete(breed);

        return "The breed was succesfully deleted";
    }
}
