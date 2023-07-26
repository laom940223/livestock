package com.laron.Livestock.management.repo;

import com.laron.Livestock.management.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends CrudRepository<RoleEntity, Long> {

    public Optional<RoleEntity> findByName(String name);
}
