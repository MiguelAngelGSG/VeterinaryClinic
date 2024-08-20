package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryPet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VeterinaryServicePetTest {

    @Mock
    private IVeterinaryRepositoryPet iVeterinaryRepositoryPet;

    @InjectMocks
    private VeterinaryServicePet veterinaryServicePet;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_if_pet_entry_is_created(){
        // Arrange
        Pet newPet = new Pet();
        when(iVeterinaryRepositoryPet.save(newPet)).thenReturn(newPet);

        // Act
        Pet result = veterinaryServicePet.createPet(newPet);

        // Assert
        assertNotNull(result);
        assertEquals(newPet, result);
        verify(iVeterinaryRepositoryPet, times(1)).save(any(Pet.class));
    }

    @Test
    void test_get_all_pets() {
        // Arrange
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Lorca");
        pet1.setAge(4);
        pet1.setBreed("Long Hair");
        pet1.setGender("Male");

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("Duque");
        pet2.setAge(7);
        pet2.setBreed("Mutt");
        pet2.setGender("Male");

        List<Pet> pets = Arrays.asList(pet1, pet2);
        when(iVeterinaryRepositoryPet.findAll()).thenReturn(pets);

        // Act
        List<Pet> result = veterinaryServicePet.getAllPets();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(pet1.getId(), result.get(0).getId());
        assertEquals(pet2.getId(), result.get(1).getId());
        verify(iVeterinaryRepositoryPet, times(1)).findAll();
    }
}

