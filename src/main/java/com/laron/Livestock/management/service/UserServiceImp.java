package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.CreateUserRequest;
import com.laron.Livestock.management.entities.RoleEntity;
import com.laron.Livestock.management.entities.UserEntity;
import com.laron.Livestock.management.exceptions.CustomFieldException;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.repo.RoleRepository;
import com.laron.Livestock.management.repo.UserRepository;
import com.laron.Livestock.management.utils.AppError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements  UserService{


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserEntity> getAllUsers() {


        var users = StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();


        return users;

    }

    @Override
    public UserEntity createUser(CreateUserRequest user) {


        RoleEntity savedRole = null;

        var role = roleRepository.findByName(user.getRole());
        var defaultUser = roleRepository.findByName("USER");


        savedRole = role.orElseGet(defaultUser::get);

            log.error(savedRole.getName());


        var eUser = UserEntity.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(savedRole)
                .build();


        return userRepository.save(eUser);

    }

    @Override
    public String deleteUser(Long id) {

        userRepository.findById(id).orElseThrow(()-> new ResourceNotFound("The user was not found"));
        userRepository.deleteById(id);
        return "User deleted succesfully";

    }

    @Override
    public UserEntity getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Resource notfound"));



    }
}
