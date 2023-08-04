package com.laron.Livestock.management.controller;


import com.laron.Livestock.management.dtos.LogInRequest;
import com.laron.Livestock.management.dtos.RegisterUserRequest;
import com.laron.Livestock.management.service.AuthService;
import com.laron.Livestock.management.utils.AppResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LogInRequest user){

            return ResponseEntity
                    .ok(

                    AppResponse.builder()
                            .data(authService.logIn(user))
                            .build()
            );
    }



    @PostMapping(path="/register")
    public ResponseEntity<AppResponse<?>> registration(@Valid @RequestBody RegisterUserRequest user){


        return ResponseEntity.ok(
                AppResponse.builder()
                        .data(authService.register(user))
                        .build()
        );





    }


}
