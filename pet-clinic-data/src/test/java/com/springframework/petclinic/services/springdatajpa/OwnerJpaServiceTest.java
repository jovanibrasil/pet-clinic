package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private OwnerJpaService ownerJpaService;
    private Owner owner;

    @BeforeEach
    void setUp() {
        this.owner = Owner.builder().id(1L).lastName("Brasil").build();
    }

    @Test
    void save(){
        Owner returnedOwner = Owner.builder().id(2L).lastName("Brasil").build();
        when(this.ownerRepository.save(any())).thenReturn(returnedOwner);
        Owner owner = this.ownerJpaService.save(returnedOwner);
        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void findById(){
        when(this.ownerRepository.findById(1L)).thenReturn(Optional.of(this.owner));
        Owner brasil = this.ownerJpaService.findById(1L);
        assertEquals(1L, brasil.getId());
        verify(ownerRepository).findById(any());
    }

    @Test
    void findByIdNotFound(){
        when(this.ownerRepository.findById(1L)).thenReturn(Optional.empty());
        Owner brasil = this.ownerJpaService.findById(1L);
        assertNull(brasil);
        verify(ownerRepository).findById(any());
    }


    @Test
    void findAll(){
        when(this.ownerRepository.findAll()).thenReturn(Arrays.asList(owner, Owner.builder().id(2L).lastName("Oliveira").build()));
        Set<Owner> owners = this.ownerJpaService.findAll();
        assertEquals(2, owners.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findByLastName() {
        when(this.ownerRepository.findByLastName(any())).thenReturn(this.owner);
        Owner brasil = this.ownerJpaService.findByLastName("Brasil");
        assertEquals("Brasil", brasil.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void delete(){
        this.ownerJpaService.delete(this.owner);
        verify(ownerRepository, times(1)).delete(any()); // default is 1 time
    }

    @Test
    void deleteById(){
        this.ownerJpaService.deleteById(1L);
        verify(ownerRepository).deleteById(any()); // default is 1 time
    }

}