package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryOwner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VeterinaryServiceOwnerTest {

    @Mock
    private IVeterinaryRepositoryOwner iVeterinaryRepositoryOwnerMock;

    @InjectMocks
    private VeterinaryServiceOwner veterinaryServiceOwner;

    private List<Owner> ownersList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ownersList = new ArrayList<>();

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("John Doe");
        owner1.setPhone("123-456-7890");
        owner1.setEmail("john.doe@example.com");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Jane Smith");
        owner2.setPhone("098-765-4321");
        owner2.setEmail("jane.smith@example.com");

        ownersList.add(owner1);
        ownersList.add(owner2);
    }

    @Test
    void getAllOwners() {
        when(iVeterinaryRepositoryOwnerMock.findAll()).thenReturn(ownersList);

        List<Owner> allOwners = veterinaryServiceOwner.getAllOwners();
        assertEquals(2, allOwners.size());
        assertEquals("John Doe", allOwners.get(0).getName());
        assertEquals("Jane Smith", allOwners.get(1).getName());
    }
}

