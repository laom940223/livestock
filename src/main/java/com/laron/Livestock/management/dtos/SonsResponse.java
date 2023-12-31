package com.laron.Livestock.management.dtos;

import com.laron.Livestock.management.entities.EAnimalSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SonsResponse {


    private String name;

    private Long id;

    private String breed;
    private EAnimalSex sex;

    private LocalDate dob;

    private Boolean inFarm;

}
