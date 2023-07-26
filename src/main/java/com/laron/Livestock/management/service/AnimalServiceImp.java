package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateAnimalRequest;
import com.laron.Livestock.management.dtos.SonsResponse;
import com.laron.Livestock.management.entities.AnimalEntity;
import com.laron.Livestock.management.entities.EAnimalSex;
import com.laron.Livestock.management.exceptions.CustomFieldException;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.repo.AnimalRepository;
import com.laron.Livestock.management.repo.BreedRepository;
import com.laron.Livestock.management.utils.AppError;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class AnimalServiceImp implements AnimalService{


    private final AnimalRepository animalRepo;
    private final BreedRepository breedRepo;

    public AnimalServiceImp(AnimalRepository animalRepo, BreedRepository breedRepo) {
        this.animalRepo = animalRepo;
        this.breedRepo = breedRepo;
    }

    @Override
    public List<AnimalEntity> getAllAnimas() {
        return StreamSupport.stream(animalRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public AnimalEntity getAnimalById(Long id) {

        return animalRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The animal with id: "+ id +" was not found"));
    }

    @Override
    public AnimalEntity createAnimal(CreateAnimalRequest animal) {



        var breed = breedRepo.findById(animal.getBreed_id())
                .orElseThrow( ()-> new CustomFieldException("Error", new AppError("breed_id", "The breed you provide does not exist")));


        AnimalEntity father = null;
        AnimalEntity mother = null;

        if(Objects.nonNull( animal.getFather_id()) ){
            father = animalRepo.findById(animal.getFather_id())
                    .orElseThrow(()-> new CustomFieldException("Error", new AppError("father_id", "The father you provide does not exist")));
            if(father.getSex()  == EAnimalSex.FEMALE) throw new CustomFieldException("Error", new AppError("father_id", "You can not add a cow as a father"));
        }

        if(Objects.nonNull( animal.getMother_id()) ){
            mother = animalRepo.findById(animal.getFather_id())
                    .orElseThrow(()-> new CustomFieldException("Error", new AppError("father_id", "The father you provide does not exist")));
            if(mother.getSex()  == EAnimalSex.MALE) throw new CustomFieldException("Error", new AppError("mother_id", "You can not add a bull as a mother"));
        }


        AnimalEntity animalToSave = AnimalEntity.builder()
                .breed(breed)
                .name(animal.getName())
                .dob(LocalDate.parse(animal.getDob()))
                .sex(EAnimalSex.valueOf(animal.getSex()))
                .isInFarm(true)
                .father(father)
                .mother(mother)
                .build();


        return animalRepo.save(animalToSave);


    }

    @Override
    public String deleteAnimal(Long id) {


        animalRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Animal was not found"));

        animalRepo.deleteById(id);

        return "Animal deleted successfully";

    }

    @Override
    public List<SonsResponse> getAnimalSons(Long id) {


            var parent = animalRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The parent that you provide does not exist"));

            if(parent.getSex()== EAnimalSex.MALE){

                return animalRepo.getFatherSons(id).stream().map(
                        son -> SonsResponse.builder()
                                .id(son.getId())
                                .name(son.getName())
                                .breed(son.getBreed().getName())
                                .inFarm(son.isInFarm())
                                .dob(son.getDob())
                                .build()

                ).collect(Collectors.toList());

            }


            return animalRepo.getMotherSons(id).stream().map(
                    son -> SonsResponse.builder()
                            .id(son.getId())
                            .name(son.getName())
                            .breed(son.getBreed().getName())
                            .inFarm(son.isInFarm())
                            .dob(son.getDob())
                            .build()

            ).collect(Collectors.toList());


    }
}
