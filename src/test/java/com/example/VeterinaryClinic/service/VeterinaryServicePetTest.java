package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryPet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VeterinaryServicePetTest {

    @Mock
    private IVeterinaryRepositoryPet iVeterinaryRepositoryPet;

    @InjectMocks
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
    void getAllPets() {
        when(iVeterinaryRepositoryPet.findAll()).thenReturn(petList);

        List<Pet> allPets = veterinaryServicePet.getAllPets();
        assertEquals(2, allPets.size());
        assertEquals("Buddy", allPets.get(0).getName());
        assertEquals("Mittens", allPets.get(1).getName());
    }


}

