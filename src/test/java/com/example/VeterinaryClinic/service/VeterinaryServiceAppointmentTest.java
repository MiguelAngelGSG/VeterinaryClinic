package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VeterinaryServiceAppointmentTest {

    @Mock
    private IVeterinaryRepositoryAppointment iVeterinaryRepositoryAppointment;

    @InjectMocks
    private VeterinaryServiceAppointment veterinaryServiceAppointment;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_if_appointment_entry_is_created(){
        // Arrange
        Appointment newAppointment = new Appointment();
        when(iVeterinaryRepositoryAppointment.save(newAppointment)).thenReturn(newAppointment);

        // Act
        Appointment result = veterinaryServiceAppointment.createAppointment(newAppointment);

        // Assert
        assertNotNull(result);
        assertEquals(newAppointment, result);
        verify(iVeterinaryRepositoryAppointment, times(1)).save(any(Appointment.class));
    }

    @Test
    void test_if_appointment_entry_is_deleted_by_id() {
        // Arrange

        Long appointmentId = 1L;

        // Act

        veterinaryServiceAppointment.deleteAppointment(appointmentId);

        // Assert

        verify(iVeterinaryRepositoryAppointment, times(1)).deleteById(appointmentId);
    }
}
