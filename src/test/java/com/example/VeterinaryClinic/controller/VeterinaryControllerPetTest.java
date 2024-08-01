package com.example.VeterinaryClinic.controller;
import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.service.VeterinaryServicePet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(VeterinaryControllerPet.class)
public class VeterinaryControllerPetTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeterinaryServicePet veterinaryServicePet;

    private List<Pet> petList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        petList = new ArrayList<>();

        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Buddy");
        pet1.setAge(3);
        pet1.setBreed("Golden Retriever");
        pet1.setGender("Male");

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("Mittens");
        pet2.setAge(2);
        pet2.setBreed("Tabby");
        pet2.setGender("Female");

        petList.add(pet1);
        petList.add(pet2);
    }

    @Test
    void getAllPets() throws Exception {
        when(veterinaryServicePet.getAllPets()).thenReturn(petList);

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Buddy")))
                .andExpect(jsonPath("$[1].name", is("Mittens")));
    }
}



