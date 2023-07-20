package com.laron.Livestock.management.repo;

import com.laron.Livestock.management.dtos.SonsResponse;
import com.laron.Livestock.management.entities.AnimalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {


    @Query(value = "SELECT * FROM animals a WHERE a.father_id = ?1", nativeQuery = true)
    public List<AnimalEntity> getFatherSons(Long id);

    @Query(value = "SELECT * FROM animals a  WHERE a.mother_id = ?1", nativeQuery = true)
    public List<AnimalEntity> getMotherSons(Long id);


}
