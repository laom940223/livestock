package com.laron.Livestock.management.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.laron.Livestock.management.entities.BreedEntity;
import com.laron.Livestock.management.entities.EAnimalSex;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateAnimalRequest {


    @Size(min = 4, max = 60, message = "Provide a name between 4 and 60 characters")
    @NotBlank
    private String name;




    @JsonFormat( pattern = "yyyy-MM-dd")
    @NotBlank
    private String dob;


    @NotBlank
    private String sex;

    @NotNull
    private Long breed_id;
}
