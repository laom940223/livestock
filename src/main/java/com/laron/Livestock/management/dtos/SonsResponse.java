package com.laron.Livestock.management.dtos;

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

    private LocalDate dob;
    private Boolean inFarm;

}
