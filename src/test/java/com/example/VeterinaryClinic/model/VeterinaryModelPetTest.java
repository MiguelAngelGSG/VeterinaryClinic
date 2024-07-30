package com.example.VeterinaryClinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VeterinaryModelPetTest {

    private Pet pet;

    @BeforeEach
    void setUp() {
        pet = new Pet();
    }

    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        String name = "Lorca";
        int age = 4;
        String breed = "Long Hair";
        String gender = "Male";
        Owner owner = new Owner();
        Appointment appointment = new Appointment();

        pet.setId(id);
        pet.setName(name);
        pet.setAge(age);
        pet.setBreed(breed);
        pet.setGender(gender);
        pet.setOwner(owner);
        pet.setAppointment(appointment);

        assertEquals(id, pet.getId());
        assertEquals(name, pet.getName());
        assertEquals(age, pet.getAge());
        assertEquals(breed, pet.getBreed());
        assertEquals(gender, pet.getGender());
        assertEquals(owner, pet.getOwner());
        assertEquals(appointment, pet.getAppointment());
    }

    @Test
    void testNoArgsConstructor() {
        Pet newPet = new Pet();

        assertNull(newPet.getId());
        assertNull(newPet.getName());
        assertEquals(0, newPet.getAge());
        assertNull(newPet.getBreed());
        assertNull(newPet.getGender());
        assertNull(newPet.getOwner());
        assertNull(newPet.getAppointment());
    }

    @Test
    void testOwnerRelationship() {
        Owner owner = new Owner();
        pet.setOwner(owner);
        assertEquals(owner, pet.getOwner());
    }

    @Test
    void testAppointmentRelationship() {
        Appointment appointment = new Appointment();
        pet.setAppointment(appointment);
        assertEquals(appointment, pet.getAppointment());
    }
}