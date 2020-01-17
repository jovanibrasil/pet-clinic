package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    private PetService petService;
    @Mock
    private OwnerService ownerService;
    @Mock
    private PetTypeService petTypeService;

    @InjectMocks
    private PetController petController;

    private Owner owner;
    private Set<PetType> petTypes;

    private MockMvc mockMvc;
    private Pet pet;

    @BeforeEach
    void setUp() {
        this.petTypes = new HashSet<>();
        this.petTypes.add(PetType.builder().id(1L).name("DOG").build());
        this.petTypes.add(PetType.builder().id(2L).name("CAT").build());
        this.owner = Owner.builder().id(1L).lastName("Owner").build();
        this.pet = new Pet();
        this.pet.setId(1L);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.petController)
                .build();
        when(this.ownerService.findById(anyLong())).thenReturn(this.owner);
        when(this.petTypeService.findAll()).thenReturn(this.petTypes);
    }

    @Test
    void initCreatePetForm() throws Exception {
        this.mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().is(200))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(this.petService);
    }

    @Test
    void processCreatePetForm() throws Exception {
        this.mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(this.petService).save(any());
    }

    @Test
    void initUpdatePetForm() throws Exception {
        when(this.petService.findById(any())).thenReturn(pet);
        this.mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().is(200))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"));
        verify(this.petService).findById(ArgumentMatchers.any());
    }

    @Test
    void processUpdatePetForm() throws Exception {
        when(this.petService.save(any())).thenReturn(this.pet);
        this.mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("pet"));
        verify(this.petService).save(ArgumentMatchers.any());
    }

}