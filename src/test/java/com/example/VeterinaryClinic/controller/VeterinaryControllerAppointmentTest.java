package com.example.VeterinaryClinic.controller;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.service.VeterinaryServiceAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(VeterinaryControllerAppointment.class)
public class VeterinaryControllerAppointmentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeterinaryServiceAppointment veterinaryServiceAppointment;

    private List<Appointment> appointmentList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        appointmentList = new ArrayList<>();

        Appointment appointment1 = new Appointment();
        appointment1.setName("Diego Doe");
        appointment1.setConsultationType("Normal");
        appointment1.setReason("hair cut");
        appointment1.setStatus("on date");
        appointment1.setDateTime("07-30-2024");

        Appointment appointment2 = new Appointment();
        appointment2.setName("Maria Doe");
        appointment2.setConsultationType("Urgent");
        appointment2.setReason("fever");
        appointment2.setStatus("on date");
        appointment2.setDateTime("07-30-2024");

        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
    }

    @Test
    void getAllAppointments() throws Exception {
        when(veterinaryServiceAppointment.getAlLAppointments()).thenReturn(appointmentList);


        mockMvc.perform(get("/appointment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Diego Doe")))
                .andExpect(jsonPath("$[0].consultationType", is("Normal")))
                .andExpect(jsonPath("$[1].name", is("Maria Doe")))
                .andExpect(jsonPath("$[1].consultationType", is("Urgent")));
    }
}
