package com.laron.Livestock.management.service;

import com.laron.Livestock.management.entities.RoleEntity;
import com.laron.Livestock.management.entities.UserEntity;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImp implements  RoleService{


    private final RoleRepository roleRepository;


    @Override
    public RoleEntity createRole(String name) {




        return roleRepository.save(
                RoleEntity.builder()
                        .name(name)
                        .build()
        );



    }

    @Override
    public List<RoleEntity> getAllRoles() {

        return StreamSupport.stream(roleRepository.findAll().spliterator(), false).map(
                user -> RoleEntity.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build()
        ).collect(Collectors.toList());

    }

    @Override
    public RoleEntity getRoleById(Long id) {


        return roleRepository.findById(id).orElseThrow(()-> new ResourceNotFound("The Role you provide does not exist"));


    }

    @Override
    public String deleteRole(Long id) {

        roleRepository.findById(id).orElseThrow(()-> new ResourceNotFound("The Role you provide does not exist"));
        roleRepository.deleteById(id);


        return "Role deleted succesfully";

    }
}
