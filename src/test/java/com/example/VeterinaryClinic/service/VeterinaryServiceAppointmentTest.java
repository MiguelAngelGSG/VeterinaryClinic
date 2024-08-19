package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VeterinaryServiceAppointmentTest {

    @Mock
    private IVeterinaryRepositoryAppointment iVeterinaryRepositoryAppointment;

    @InjectMocks
    private VeterinaryServiceAppointment veterinaryServiceAppointment;

    private List<Appointment> appointmentList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        appointmentList = new ArrayList<>();

        Appointment appointment1 = new Appointment();
        appointment1.setName("Bolita");
        appointment1.setConsultationType("Normal");
        appointment1.setReason("hair cut");
        appointment1.setStatus("on date");
        appointment1.setDateTime("07-30-2024");

        Appointment appointment2 = new Appointment();
        appointment2.setName("Jack");
        appointment2.setConsultationType("Urgent");
        appointment2.setReason("fever");
        appointment2.setStatus("on date");
        appointment2.setDateTime("07-30-2024");

        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
    }

    @Test
    void getAllAppointments() {
        when(iVeterinaryRepositoryAppointment.findAll()).thenReturn(appointmentList);

        List<Appointment> allAppointments = veterinaryServiceAppointment.getAlLAppointments();
        assertEquals(2, allAppointments.size());
        assertEquals("Diego Doe", allAppointments.get(0).getName());
        assertEquals("Maria Doe", allAppointments.get(1).getName());
    }
}
