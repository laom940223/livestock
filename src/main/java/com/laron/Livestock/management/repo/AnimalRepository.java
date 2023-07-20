package com.laron.Livestock.management.repo;

import com.laron.Livestock.management.entities.AnimalEntity;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {
}
