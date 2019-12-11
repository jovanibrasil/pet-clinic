package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private final Long ownerId = 1L;

    @BeforeEach
    void setUp() {
        this.ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        this.ownerMapService.save(Owner.builder().id(this.ownerId).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = this.ownerMapService.findAll();
        assertEquals(this.ownerId, owners.size());
    }

    @Test
    void findById() {
        Owner owner = this.ownerMapService.findById(ownerId);
        assertNotNull(owner);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = this.ownerMapService.save(owner2);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = this.ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        Owner owner = this.ownerMapService.findById(this.ownerId);
        this.ownerMapService.delete(owner);
        assertEquals(0, this.ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        this.ownerMapService.deleteById(ownerId);
        assertEquals(0, this.ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        this.ownerMapService.save(Owner.builder().id(ownerId).lastName("Brasil").build());
        Owner owner = this.ownerMapService.findByLastName("Brasil");
        assertNotNull(owner);
        assertEquals("Brasil", owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = this.ownerMapService.findByLastName("Brasil");
        assertNull(owner);
    }

}