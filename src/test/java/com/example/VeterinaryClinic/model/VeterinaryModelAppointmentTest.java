package com.example.VeterinaryClinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VeterinaryModelAppointmentTest {

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment();
    }

    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        String dateTime = "05/08/2024, 12:00";
        String name = "Routine Check-up";
        String consultationType = "General";
        String reason = "Annual health check";
        String status = "Scheduled";
        Pet pet = new Pet();

        appointment.setId(id);
        appointment.setDateTime(dateTime);
        appointment.setName(name);
        appointment.setConsultationType(consultationType);
        appointment.setReason(reason);
        appointment.setStatus(status);
        appointment.setPet(pet);

        assertEquals(id, appointment.getId());
        assertEquals(dateTime, appointment.getDateTime());
        assertEquals(name, appointment.getName());
        assertEquals(consultationType, appointment.getConsultationType());
        assertEquals(reason, appointment.getReason());
        assertEquals(status, appointment.getStatus());
        assertEquals(pet, appointment.getPet());
    }

    @Test
    void testNoArgsConstructor() {
        Appointment newAppointment = new Appointment();

        assertNull(newAppointment.getId());
        assertNull(newAppointment.getDateTime());
        assertNull(newAppointment.getName());
        assertNull(newAppointment.getConsultationType());
        assertNull(newAppointment.getReason());
        assertNull(newAppointment.getStatus());
        assertNull(newAppointment.getPet());
    }

    @Test
    void testPetRelationship() {
        Pet pet = new Pet();
        appointment.setPet(pet);
        assertEquals(pet, appointment.getPet());
    }
}