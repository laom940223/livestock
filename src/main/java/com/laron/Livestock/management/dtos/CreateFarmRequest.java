package com.laron.Livestock.management.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFarmRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;
}
