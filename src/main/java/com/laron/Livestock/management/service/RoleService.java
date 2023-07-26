package com.laron.Livestock.management.service;


import com.laron.Livestock.management.entities.RoleEntity;

import java.util.List;

public interface RoleService {

    public RoleEntity createRole(String name);

    public List<RoleEntity> getAllRoles();


    public RoleEntity getRoleById(Long id);

    public String deleteRole(Long id);

}
