package com.laron.Livestock.management.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "farms")
@ToString
public class FarmEntity {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;



    @OneToMany(mappedBy = "farm",fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST})
    private List<AnimalEntity> animals;

}
