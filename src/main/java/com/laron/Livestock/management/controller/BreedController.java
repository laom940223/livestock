package com.laron.Livestock.management.controller;

import com.laron.Livestock.management.dtos.CreateBreedRequest;
import com.laron.Livestock.management.service.BreedService;
import com.laron.Livestock.management.utils.AppError;
import com.laron.Livestock.management.utils.AppResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/breeds")
public class BreedController {


    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }


    @GetMapping
    public ResponseEntity<AppResponse<?>> getAllBreeds(){

        return ResponseEntity.status(HttpStatus.OK).body(
                AppResponse.builder()
                        .data(
                                breedService.getAllBreeds()
                        )
                        .errors(null)
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<AppResponse<?>> createBreed (@Valid  @RequestBody CreateBreedRequest breed){

        return ResponseEntity.status(HttpStatus.CREATED).body(

                AppResponse.builder()
                        .data(
                                breedService.createBreed(breed)
                        )
                        .errors(null)
                        .build()
        );
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<AppResponse<?>>  deleteBreed (@PathVariable Long id){

        return ResponseEntity.ok(

                AppResponse.builder()
                        .data(  breedService.deleteBreedById(id) )
                        .build()

        );
    }


}
