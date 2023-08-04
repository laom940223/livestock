package com.laron.Livestock.management.service;

import com.laron.Livestock.management.dtos.LogInRequest;
import com.laron.Livestock.management.dtos.LogInResponse;
import com.laron.Livestock.management.dtos.RegisterUserRequest;
import com.laron.Livestock.management.entities.UserEntity;
import com.laron.Livestock.management.exceptions.CustomFieldException;
import com.laron.Livestock.management.repo.RoleRepository;
import com.laron.Livestock.management.repo.UserRepository;
import com.laron.Livestock.management.utils.AppError;
import com.laron.Livestock.management.utils.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImp  implements  AuthService{

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LogInResponse logIn(LogInRequest user) {


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        var eUser = userRepository.findByUsername(user.getUsername()).orElseThrow(()-> new CustomFieldException("", new AppError("username", "The username was not founds")));
        var token = jwtService.generateToken(eUser);

        return LogInResponse.builder()
                .token(token)
                .build();

    }

    @Override
    public LogInResponse register(RegisterUserRequest user) {


        Optional<UserEntity> userE;
        userE = userRepository.findByUsername(user.getUsername());


        if (userE.isPresent()) throw new CustomFieldException("", AppError.builder()
                .location("username")
                .message("The username is already taken")
                .build());

        userE = userRepository.findByEmail(user.getEmail());

        if (userE.isPresent()) throw new CustomFieldException("", AppError.builder()
                .location("email")
                .message("The email is already taken")
                .build());


        var role = roleRepository.findByName("OWNER").orElseThrow(() -> new RuntimeException("Something went wrong"));


        var toSave = UserEntity.builder()
                .name(user.getName())
                .username(user.getUsername().replace(" ", ""))
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(role)
                .password(passwordEncoder.encode(user.getPassword()))
                .build();


        var savedUser = userRepository.save(toSave);

        var token = jwtService.generateToken(savedUser);


        return LogInResponse.builder()
                .token(token)
                .build();

        }
}
