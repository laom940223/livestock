package com.laron.Livestock.management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {


    @NotBlank
    @Size(min = 4, max = 50, message = "Name mus be bewteen 4 and 50")
    private String name;
}
