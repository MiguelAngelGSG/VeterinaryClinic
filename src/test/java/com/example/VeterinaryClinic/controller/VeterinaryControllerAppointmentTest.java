package com.example.VeterinaryClinic.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.service.VeterinaryServiceAppointment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class VeterinaryControllerAppointmentTest {

    private MockMvc mockMvc;

    @Mock
    private VeterinaryServiceAppointment veterinaryServiceAppointment;

    @InjectMocks
    private VeterinaryControllerAppointment veterinaryControllerAppointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veterinaryControllerAppointment).build();
    }

    @Test
    void testCreateAppointment() throws Exception {
        // Arrange
        Pet pet = new Pet();
        pet.setId(1L);

        Appointment newAppointment = new Appointment();
        newAppointment.setId(1L);
        newAppointment.setDateTime("05/08/2024 12:00");
        newAppointment.setName("Lorca");
        newAppointment.setConsultationType("General Check-up");
        newAppointment.setReason("Routine visit");
        newAppointment.setStatus("Scheduled");
        newAppointment.setPet(pet);

        when(veterinaryServiceAppointment.createAppointment(any(Appointment.class))).thenReturn(newAppointment);

        // Act & Assert
        mockMvc.perform(post("/api/appointment")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(newAppointment)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.dateTime").value("05/08/2024 12:00"))
                .andExpect(jsonPath("$.name").value("Lorca"))
                .andExpect(jsonPath("$.consultationType").value("General Check-up"))
                .andExpect(jsonPath("$.reason").value("Routine visit"))
                .andExpect(jsonPath("$.status").value("Scheduled"))
                .andExpect(jsonPath("$.pet.id").value(1L)); // Assuming pet's ID is 1

        verify(veterinaryServiceAppointment, times(1)).createAppointment(any(Appointment.class));
    }
}
