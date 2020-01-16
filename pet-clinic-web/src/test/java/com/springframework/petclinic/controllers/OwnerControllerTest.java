package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;
    @InjectMocks
    private OwnerController ownerController;

    private Set<Owner> ownerSet;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.ownerSet = new HashSet<>();
        this.ownerSet.add(Owner.builder().id(1L).lastName("First").build());
        this.ownerSet.add(Owner.builder().id(2L).lastName("Second").build());
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.ownerController)
                .build();
    }

    @Test
    void findOwner() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
        Mockito.verifyNoInteractions(this.ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(this.ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays
                        .asList(Owner.builder().id(1L).lastName("First").build(),
                                Owner.builder().id(2L).lastName("First").build()));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is(200)) // isOk()
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(this.ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).lastName("First").build()));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
        ;
    }

    @Test
    void displayOwner() throws Exception {
        when(this.ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).lastName("First").build());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
                .andExpect(status().is(200)) // isOk()
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

}