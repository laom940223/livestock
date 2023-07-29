package com.laron.Livestock.management.service;


import com.laron.Livestock.management.dtos.LogInRequest;
import com.laron.Livestock.management.dtos.LogInResponse;
import com.laron.Livestock.management.dtos.RegisterUserRequest;

public interface AuthService {

    public LogInResponse logIn(LogInRequest user);

    public LogInResponse register(RegisterUserRequest user);
}
