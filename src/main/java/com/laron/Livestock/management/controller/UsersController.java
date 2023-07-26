package com.laron.Livestock.management.controller;


import com.laron.Livestock.management.dtos.CreateUserRequest;
import com.laron.Livestock.management.service.UserService;
import com.laron.Livestock.management.utils.AppResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/users")
@Slf4j
public class UsersController {


    private final UserService userService;

    @GetMapping
    public ResponseEntity<AppResponse<?>>  getAllUsers(){

        return ResponseEntity.ok(
                AppResponse.builder()
                        .data(userService.getAllUsers())
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<AppResponse<?>> createUser(@Valid @RequestBody CreateUserRequest user){
        return ResponseEntity.ok(

                AppResponse.builder()
                        .data(userService.createUser(user))
                        .build()
        );
    }



    @GetMapping(path = "/{id}")
    public ResponseEntity<AppResponse<?>> getUserId(@PathVariable Long id){
        return ResponseEntity.ok(

                AppResponse.builder()
                        .data(userService.getUserById(id))
                        .build()

        );
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<AppResponse<?>> deleteUserById(@PathVariable Long id){

        return ResponseEntity.ok(

                AppResponse.builder()
                        .data(userService.deleteUser(id))
                        .build()

        );
    }
}
