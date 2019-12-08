package com.springframework.petclinic.model;

import javax.persistence.*;

@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
