package com.laron.Livestock.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {


    @NotBlank
    @Size(min = 2, max = 60)
    private String name;

    @NotBlank
    @Size(min = 2, max = 60)
    private String lastname;

    @NotBlank
    @Size(min = 2, max = 60)
    private String password;

    @NotBlank
    @Size(min = 2, max = 60)
    private String username;

    @NotBlank
    @Size(min = 2, max = 80)
    private String email;
}
