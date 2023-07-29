package com.laron.Livestock.management.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInRequest {


    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
