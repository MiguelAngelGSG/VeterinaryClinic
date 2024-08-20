package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryAppointment;
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
    void test_get_all_appointments() {
        // Arrange
        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);
        Appointment appointment2 = new Appointment();
        appointment2.setId(2L);

        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);
        when(iVeterinaryRepositoryAppointment.findAll()).thenReturn(appointments);

        // Act
        List<Appointment> result = veterinaryServiceAppointment.getAlLAppointments();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(appointment1.getId(), result.get(0).getId());
        assertEquals(appointment2.getId(), result.get(1).getId());
        verify(iVeterinaryRepositoryAppointment, times(1)).findAll();
    }
}

