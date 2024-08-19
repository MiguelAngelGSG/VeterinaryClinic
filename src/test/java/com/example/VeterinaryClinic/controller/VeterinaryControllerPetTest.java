package com.example.VeterinaryClinic.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

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

class VeterinaryControllerPetTest {

    private MockMvc mockMvc;

    @Mock
    private VeterinaryServicePet veterinaryServicePet;

    @InjectMocks
    private VeterinaryControllerPet veterinaryControllerPet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veterinaryControllerPet).build();
    }

    @Test
    void testCreatePet() throws Exception {
        // Arrange
        Pet newPet = new Pet();
        newPet.setId(1L);
        newPet.setName("Lorca");
        newPet.setAge(4);
        newPet.setBreed("Long Hair");
        newPet.setGender("Male");

        when(veterinaryServicePet.createPet(any(Pet.class))).thenReturn(newPet);

        // Act & Assert
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
void testDeletePet() throws Exception {
    // Arrange
    Long petId = 1L;

    // Mock the service to do nothing when deletePet is called
    doNothing().when(veterinaryServicePet).deletePet(petId);

    // Act & Assert
    mockMvc.perform(delete("/pet/{id}", petId))
           .andExpect(status().isOk());  // Or .andExpect(status().isNoContent());

    // Verify that the service's deletePet method was called once
    verify(veterinaryServicePet, times(1)).deletePet(petId);
}
}