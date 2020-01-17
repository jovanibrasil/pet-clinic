package com.springframework.petclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @OneToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(Long id, LocalDate birthDate, String name, Owner owner, PetType petType, Set<Visit> visits) {
        super(id);
        this.birthDate = birthDate;
        this.name = name;
        this.owner = owner;
        this.petType = petType;
        if(visits != null || visits.size() > 0){
            this.visits = visits;
        }
    }
}
