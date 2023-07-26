package com.laron.Livestock.management.dtos;

import com.laron.Livestock.management.entities.RoleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {


    @NotBlank(message = "Name can not be empty")
    @Size(min = 4, max = 50, message = "Name must be between 4 and 50")
    private String name;

    @NotBlank(message = "Lastname can not be empty")
    @Size(min = 4, max = 50, message = "Lastname must be between 4 and 50")
    private String lastname;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 4, max = 50, message = "Password must be between 4 and 50")
    private String password;

    @NotBlank(message = "Username can not be empty")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50")
    private String username;

    @NotBlank(message = "Email can not be empty")
    @Email
    @Size(min = 4, max = 50, message = "Email must be between 4 and 50")
    private String email;

    @Size(min = 4, max = 50, message = "Role must be between 4 and 50")
    private String role;
}
