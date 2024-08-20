package com.example.VeterinaryClinic.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.service.VeterinaryServicePet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

class VeterinaryControllerPetTest {

    private MockMvc mockMvc;
    private Pet newPet;
    private Pet anotherPet;

    @Mock
    private VeterinaryServicePet veterinaryServicePet;

    @InjectMocks
    private VeterinaryControllerPet veterinaryControllerPet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veterinaryControllerPet).build();

        newPet = new Pet();
        newPet.setId(1L);
        newPet.setName("Lorca");
        newPet.setAge(4);
        newPet.setBreed("Long Hair");
        newPet.setGender("Male");

        anotherPet = new Pet();
        anotherPet.setId(2L);
        anotherPet.setName("Duque");
        anotherPet.setAge(7);
        anotherPet.setBreed("Mutt");
        anotherPet.setGender("Male");
    }

    @Test
    void testCreatePet() throws Exception {
        when(veterinaryServicePet.createPet(any(Pet.class))).thenReturn(newPet);

        mockMvc.perform(post("/pet")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(newPet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Lorca"))
                .andExpect(jsonPath("$.age").value(4))
                .andExpect(jsonPath("$.breed").value("Long Hair"))
                .andExpect(jsonPath("$.gender").value("Male"));

        verify(veterinaryServicePet, times(1)).createPet(any(Pet.class));
    }

    @Test
    void testCreatePetWithDifferentDetails() throws Exception {
        newPet.setId(2L);
        newPet.setName("Duque");
        newPet.setAge(7);
        newPet.setBreed("Mutt");
        newPet.setGender("Male");

        when(veterinaryServicePet.createPet(any(Pet.class))).thenReturn(newPet);

        mockMvc.perform(post("/pet")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(newPet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Duque"))
                .andExpect(jsonPath("$.age").value(7))
                .andExpect(jsonPath("$.breed").value("Mutt"))
                .andExpect(jsonPath("$.gender").value("Male"));

        verify(veterinaryServicePet, times(1)).createPet(any(Pet.class));
    }

    @Test
    void testGetAllPets() throws Exception {
        List<Pet> pets = Arrays.asList(newPet, anotherPet);
        when(veterinaryServicePet.getAllPets()).thenReturn(pets);

        mockMvc.perform(get("/pet")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Lorca"))
                .andExpect(jsonPath("$[0].age").value(4))
                .andExpect(jsonPath("$[0].breed").value("Long Hair"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Duque"))
                .andExpect(jsonPath("$[1].age").value(7))
                .andExpect(jsonPath("$[1].breed").value("Mutt"))
                .andExpect(jsonPath("$[1].gender").value("Male"));

        verify(veterinaryServicePet, times(1)).getAllPets();
    }
}
