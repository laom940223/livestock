package com.laron.Livestock.management.controller;


import com.laron.Livestock.management.dtos.CreateRoleRequest;
import com.laron.Livestock.management.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/roles")
public class RoleController {


    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(
                roleService.getAllRoles()
        );



    }


    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody CreateRoleRequest role){


        return ResponseEntity.ok(
                roleService.createRole(role.getName())
        );
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteRole ( @PathVariable Long id){

        return ResponseEntity.ok(
                roleService.deleteRole(id)
        );

    }

}
