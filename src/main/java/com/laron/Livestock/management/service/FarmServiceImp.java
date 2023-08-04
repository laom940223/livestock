package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateFarmRequest;
import com.laron.Livestock.management.entities.FarmEntity;
import com.laron.Livestock.management.entities.UserEntity;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.repo.FarmRepository;
import com.laron.Livestock.management.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FarmServiceImp implements FarmService{


    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    @Override
    public List<FarmEntity> getMyFarms() {

        return farmRepository.getMyFarms(getCurrentUser().getId());
    }

    @Override
    public FarmEntity createFarm(CreateFarmRequest farm) {

            var user = getCurrentUser();
            var euser = userRepository.findByUsername(user.getUsername()).orElseThrow(()->new RuntimeException("err"));



//
        var eFarm = FarmEntity.builder()
                .name(farm.getName())
                .address(farm.getAddress())
                .owner(euser)
                .build();


        return farmRepository.save(eFarm);
    }

    @Override
    public String deleteFarm(Long id) {
        return null;
    }

    @Override
    public FarmEntity getFarmDetails(Long id) {

         return farmRepository.findById(id).orElseThrow( ()-> new ResourceNotFound("The farm with id:" + " does not exist"));

    }


    UserEntity getCurrentUser(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
            username =  ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }


        ;return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Something went wrong"));

    }






}
