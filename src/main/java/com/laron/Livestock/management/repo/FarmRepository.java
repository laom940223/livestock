package com.laron.Livestock.management.repo;

import com.laron.Livestock.management.entities.AnimalEntity;
import com.laron.Livestock.management.entities.FarmEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface FarmRepository extends CrudRepository<FarmEntity, Long> {

    @Query(value = "SELECT * FROM farms a WHERE a.owner_id = ?1", nativeQuery = true)
    public List<FarmEntity> getMyFarms(Long id);

}

//
//SELECT * FROM caddle.farms f
//        inner join  caddle.users u
//        on f.owner_id= u.id
//        where f.owner_id = 352
//        ;