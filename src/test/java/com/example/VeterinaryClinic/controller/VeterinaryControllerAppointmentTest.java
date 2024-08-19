package com.example.VeterinaryClinic.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

        // Initialize the pet and appointment objects here
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

        // Initialize another appointment with different values
        anotherAppointment = new Appointment();
        anotherAppointment.setId(2L);
        anotherAppointment.setDateTime("06/09/2024 14:30");
        anotherAppointment.setName("Duque");
        anotherAppointment.setConsultationType("Dental Cleaning");
        anotherAppointment.setReason("Teeth cleaning");
        anotherAppointment.setStatus("Scheduled");
        anotherAppointment.setPet(pet);  // Assuming the same pet is used
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
                .andExpect(jsonPath("$.pet.id").value(1L)); // Assuming pet's ID is 1

        verify(veterinaryServiceAppointment, times(1)).createAppointment(any(Appointment.class));
    }


   @Test void test_if_appointment_entry_is_deleted_by_id() throws Exception { 
    
    //Arrange
    
    Long appointmentId = 1L; doNothing().when(veterinaryServiceAppointment).deleteAppointment(appointmentId); 
    
    //Act & Assert

    mockMvc.perform(delete("/appointment/{id}", appointmentId)) .andExpect(status().isNoContent());
    verify(veterinaryServiceAppointment, times(1)).deleteAppointment(appointmentId);
   }
}
