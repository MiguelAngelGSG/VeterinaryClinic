package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryOwner;

import net.bytebuddy.description.modifier.Ownership;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        void test_if_Owner_is_deleted() {
        // Arrange

        Long ownerId = 1L;

        // Act

        veterinaryServiceOwner.deleteOwner(ownerId);

        // Assert

        verify(iVeterinaryRepositoryOwner, times(1)).deleteById(ownerId);
     }
}
