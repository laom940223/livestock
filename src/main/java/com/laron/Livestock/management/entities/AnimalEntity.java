package com.laron.Livestock.management.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="animals")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 4, max = 60, message = "Provide a name between 4 and 60 characters")
    @NotBlank
    @Column( nullable = false)
    private String name;


    @Column
    @NotBlank
    private LocalDate dob;


    @Enumerated(EnumType.STRING)
    private EAnimalSex sex;


    @Column(columnDefinition = "boolean default true")
    private boolean isInFarm;



   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn( name="breed_id")
   private BreedEntity breed;


}
