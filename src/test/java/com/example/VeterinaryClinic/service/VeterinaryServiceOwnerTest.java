package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryOwner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VeterinaryServiceOwnerTest {

    @Mock
    private IVeterinaryRepositoryOwner iVeterinaryRepositoryOwner;

    @InjectMocks
    private VeterinaryServiceOwner veterinaryServiceOwner;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

     @Test
        void test_if_owner_entry_is_created(){
        // Arrange
        Owner newOwner = new Owner();
        when(iVeterinaryRepositoryOwner.save(newOwner)).thenReturn(newOwner);

        // Act
        Owner result = veterinaryServiceOwner.createOwner(newOwner);

        // Assert
        assertNotNull(result);
        assertEquals(newOwner, result);
        verify(iVeterinaryRepositoryOwner, times(1)).save(any(Owner.class));
     }


    @Test
    void test_get_all_owners() {
        // Arrange
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("Miguel");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Isabel");

        List<Owner> owners = Arrays.asList(owner1, owner2);
        when(iVeterinaryRepositoryOwner.findAll()).thenReturn(owners);

        // Act
        List<Owner> result = veterinaryServiceOwner.getAllOwners();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(owner1.getId(), result.get(0).getId());
        assertEquals(owner2.getId(), result.get(1).getId());
        verify(iVeterinaryRepositoryOwner, times(1)).findAll();
    }
}

