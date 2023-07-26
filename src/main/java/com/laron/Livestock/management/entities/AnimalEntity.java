package com.laron.Livestock.management.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laron.Livestock.management.service.AnimalService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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



    private LocalDate dob;


    @Enumerated(EnumType.STRING)
    private EAnimalSex sex;


    @Column(columnDefinition = "boolean default true")
    private boolean isInFarm;


    @JsonIgnore
    @OneToMany(mappedBy = "mother",fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private List<AnimalEntity> mSons;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name= "mother_id")
    private AnimalEntity mother;



    @JsonIgnore
    @OneToMany(mappedBy = "father",fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private List<AnimalEntity> fSons;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name= "father_id")
    private AnimalEntity father;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name="breed_id")
    private BreedEntity breed;


}
