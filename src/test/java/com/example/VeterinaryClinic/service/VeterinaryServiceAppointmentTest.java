package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.model.Owner;
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

import java.time.LocalDateTime;

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
    void update_appointment() {
        long appointmentId = 1L;
        Appointment appointment = new Appointment();
        appointment.setDateTime(LocalDateTime.now());
        appointment.setReason("Routine visit");
        appointment.setConsultationType("General");
        appointment.setStatus(true);

        veterinaryServiceAppointment.updateAppointment(appointment, appointmentId);

        verify(iVeterinaryRepositoryAppointment).save(appointment);
        assertEquals(appointmentId, appointment.getId());
    }

}  