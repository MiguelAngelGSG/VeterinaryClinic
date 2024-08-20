package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryPet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void updatePet() {
        long id = 1L;
        Pet pet = new Pet();
        pet.setName("Lorca");
        pet.setAge(4);
        pet.setBreed("Long Hair");
        pet.setGender("Male");

        veterinaryServicePet.updatePet(pet, id);

        verify(iVeterinaryRepositoryPet).save(pet);
        assertEquals(id, pet.getId());
    }
}
