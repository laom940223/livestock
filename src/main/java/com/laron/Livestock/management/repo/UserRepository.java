package com.laron.Livestock.management.repo;

import com.laron.Livestock.management.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
