package com.laron.Livestock.management.controller;


import com.laron.Livestock.management.dtos.LogInRequest;
import com.laron.Livestock.management.exceptions.CustomFieldException;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.repo.UserRepository;
import com.laron.Livestock.management.utils.AppError;
import com.laron.Livestock.management.utils.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth/login")
@RequiredArgsConstructor
public class AuthController {



    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> authenticate( @Valid @RequestBody LogInRequest user){


            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            var eUser = userRepository.findByUsername(user.getUsername()).orElseThrow(()-> new CustomFieldException("", new AppError("username", "The username was not found")));

            var token = jwtService.generateToken(eUser);


            return ResponseEntity.ok( token );


    }


}
