package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateUserRequest;
import com.laron.Livestock.management.entities.UserEntity;

import java.util.List;

public interface UserService {



    public List<UserEntity> getAllUsers();

    public UserEntity createUser(CreateUserRequest user);

    public String deleteUser(Long id);

    public UserEntity getUserById(Long id);


}
