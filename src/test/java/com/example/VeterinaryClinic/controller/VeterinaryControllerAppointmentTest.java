package com.example.VeterinaryClinic.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import java.util.Arrays;
import java.util.List;

class VeterinaryControllerAppointmentTest {

    private MockMvc mockMvc;
    private Appointment newAppointment;
    private Appointment anotherAppointment;
    private Pet pet;

    @Mock
    private VeterinaryServiceAppointment veterinaryServiceAppointment;

    @InjectMocks
    private VeterinaryControllerAppointment veterinaryControllerAppointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veterinaryControllerAppointment).build();


        pet = new Pet();
        pet.setId(1L);

        newAppointment = new Appointment();
        newAppointment.setId(1L);
        newAppointment.setDateTime("05/08/2024 12:00");
        newAppointment.setName("Lorca");
        newAppointment.setConsultationType("General Check-up");
        newAppointment.setReason("Routine visit");
        newAppointment.setStatus("Scheduled");
        newAppointment.setPet(pet);

        anotherAppointment = new Appointment();
        anotherAppointment.setId(2L);
        anotherAppointment.setDateTime("06/09/2024 14:30");
        anotherAppointment.setName("Duque");
        anotherAppointment.setConsultationType("Dental Cleaning");
        anotherAppointment.setReason("Teeth cleaning");
        anotherAppointment.setStatus("Scheduled");
        anotherAppointment.setPet(pet);  // Suponiendo que se utiliza la misma mascota
    }

    @Test
    void testCreateAppointment() throws Exception {
        // Arrange
        when(veterinaryServiceAppointment.createAppointment(any(Appointment.class))).thenReturn(newAppointment);

        // Act & Assert
        mockMvc.perform(post("/appointment")
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
                .andExpect(jsonPath("$.pet.id").value(1L)); // Suponiendo que la ID de la mascota es 1

        verify(veterinaryServiceAppointment, times(1)).createAppointment(any(Appointment.class));
    }

    @Test
    void testGetAllAppointments() throws Exception {
        // Arrange
        List<Appointment> appointments = Arrays.asList(newAppointment, anotherAppointment);
        when(veterinaryServiceAppointment.getAlLAppointments()).thenReturn(appointments);

        // Act & Assert
        mockMvc.perform(get("/appointment")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].dateTime").value("05/08/2024 12:00"))
                .andExpect(jsonPath("$[0].name").value("Lorca"))
                .andExpect(jsonPath("$[0].consultationType").value("General Check-up"))
                .andExpect(jsonPath("$[0].reason").value("Routine visit"))
                .andExpect(jsonPath("$[0].status").value("Scheduled"))
                .andExpect(jsonPath("$[0].pet.id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].dateTime").value("06/09/2024 14:30"))
                .andExpect(jsonPath("$[1].name").value("Duque"))
                .andExpect(jsonPath("$[1].consultationType").value("Dental Cleaning"))
                .andExpect(jsonPath("$[1].reason").value("Teeth cleaning"))
                .andExpect(jsonPath("$[1].status").value("Scheduled"))
                .andExpect(jsonPath("$[1].pet.id").value(1L));

        verify(veterinaryServiceAppointment, times(1)).getAlLAppointments();
    }
}
