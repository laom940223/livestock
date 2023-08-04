package com.laron.Livestock.management.controller;

import com.laron.Livestock.management.dtos.CreateFarmRequest;
import com.laron.Livestock.management.entities.UserEntity;
import com.laron.Livestock.management.service.FarmService;
import com.laron.Livestock.management.utils.AppResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/farms")
@Slf4j
@RequiredArgsConstructor
public class FarmController {


    private final FarmService farmService;

    @GetMapping
    public ResponseEntity<AppResponse<?>> getFarms(){
        return ResponseEntity.ok(
                AppResponse.builder()
                        .data(farmService.getMyFarms())
                        .build()

        );

    }


    @PostMapping
    public ResponseEntity<AppResponse<?>> createFarm(@Valid @RequestBody CreateFarmRequest farm){
        return ResponseEntity.ok(AppResponse.builder()
                        .data(farmService.createFarm(farm))
                        .build());
    }


    @GetMapping(path="/{id}")
    public ResponseEntity<AppResponse<?>> getFarmDetails(@PathVariable Long id){


        return ResponseEntity.ok(AppResponse.builder()
                        .data(farmService.getFarmDetails(id))
                .build());

    }

}
