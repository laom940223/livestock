package com.laron.Livestock.management.controller;


import com.laron.Livestock.management.dtos.CreateAnimalRequest;
import com.laron.Livestock.management.service.AnimalService;
import com.laron.Livestock.management.utils.AppResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/animals")
public class AnimalController {


    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<AppResponse<?>> getAllAnimals(){


        return ResponseEntity.ok(

                AppResponse.builder()
                        .data(
                                animalService.getAllAnimas()
                        )
                        .build()
        );

    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<AppResponse<?>> getAnimalById(@PathVariable Long id){

        return ResponseEntity.ok(

                AppResponse.builder()
                        .data(
                                animalService.getAnimalById(id)
                        )
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<AppResponse<?>> createAnimal(@Valid @RequestBody CreateAnimalRequest animal){

        return ResponseEntity.ok(

                AppResponse.builder()
                        .data(
                                animalService.createAnimal(animal)
                        )
                        .build()
        );
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<AppResponse<?>> deleteAnimal( @PathVariable Long id){

        return ResponseEntity.ok(
                AppResponse.builder()
                        .data(
                                animalService.deleteAnimal(id)
                        )
                        .build()
        );
    }


    @GetMapping(path = "/{id}/sons")
    public ResponseEntity<AppResponse<?>> getSons(@PathVariable Long id){


        return ResponseEntity.ok(
                AppResponse.builder()
                        .data( animalService.getAnimalSons(id))
                        .build()

        );


    }

}
