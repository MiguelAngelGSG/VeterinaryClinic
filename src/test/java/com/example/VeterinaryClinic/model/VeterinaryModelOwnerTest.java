package com.example.VeterinaryClinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class VeterinaryModelOwnerTest {

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
    }

    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        String name = "Miguel";
        String phone = "+34 1234567890";
        String email = "example@example.com";
        Set<Pet> pets = new HashSet<>();

        owner.setId(id);
        owner.setName(name);
        owner.setPhone(phone);
        owner.setEmail(email);
        owner.setPets(pets);

        assertEquals(id, owner.getId());
        assertEquals(name, owner.getName());
        assertEquals(phone, owner.getPhone());
        assertEquals(email, owner.getEmail());
        assertEquals(pets, owner.getPets());
    }

    @Test
    void testNoArgsConstructor() {
        Owner newOwner = new Owner();

        assertNull(newOwner.getId());
        assertNull(newOwner.getName());
        assertNull(newOwner.getPhone());
        assertNull(newOwner.getEmail());
        assertNotNull(newOwner.getPets());
        assertTrue(newOwner.getPets().isEmpty());
    }
}