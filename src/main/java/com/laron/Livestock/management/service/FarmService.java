package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateFarmRequest;
import com.laron.Livestock.management.entities.FarmEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface FarmService {


    public List<FarmEntity> getMyFarms();

    public FarmEntity createFarm(CreateFarmRequest farm);

    public String deleteFarm(Long id);

    public FarmEntity getFarmDetails(Long id);
}
